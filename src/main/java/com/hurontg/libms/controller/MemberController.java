package com.hurontg.mars.mvc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hurontg.common.exception.AppServiceException;
import com.hurontg.common.util.Constants;
import com.hurontg.common.util.Utils;
import com.hurontg.mars.domain.AuthUser;
import com.hurontg.mars.domain.CommonText;
import com.hurontg.mars.domain.Profile;
import com.hurontg.mars.dto.ProfileDTO;
import com.hurontg.mars.security.CurrentUser;
import com.hurontg.mars.service.CommonTextService;
import com.hurontg.mars.service.MemberProfileService;

@Controller
@RequestMapping("/s/xmd")
public class MemberController extends BaseController {

	@Autowired
	ServletContext context;

	@Inject
	private CommonTextService ctSvc;

	@Inject
	private MemberProfileService memberProfileSvc;

	@Inject
	Environment env;
	
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(MemberController.class.getName());

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String getHomePage(@CurrentUser UserDetails user, Model model) throws Exception {
		// model.addAttribute("user", new AuthUser());
		// model.addAttribute("user", getCompleteMemberProfile());
		
		model.addAttribute("user", user);
		return "member/home-x.jsp";
		// return "member/videotest.jsp";
	}

	@RequestMapping(value = { "/home2" }, method = RequestMethod.GET)
	public String getHomePage2(Model model) throws Exception {
		model.addAttribute("user", new AuthUser());
		// model.addAttribute("user", getCompleteMemberProfile());
		return "member/home.jsp";
		// return "member/videotest.jsp";
	}

	@RequestMapping(value = { "/profile.json" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Profile> getMemberProfile(HttpServletResponse response) throws Exception {
		Profile profile = memberProfileSvc.loadProfileByUserId2(Utils.getAuthUser());
		AuthUser user = Utils.getAuthUser();
		profile.setFirstName(user.getFirstName());
		;
		profile.setLastName(user.getLastName());
		return new ResponseEntity<Profile>(profile, HttpStatus.OK);

	}

	private Object getCompleteMemberProfile() {
		return memberProfileSvc.loadProfileByUserId(Utils.getAuthUser().getId());
	}

	/**
	 * Edit Profile-
	 */
	@RequestMapping(value = { "/xepf" }, method = RequestMethod.GET)
	public String getEditProfilePage(Model model) throws Exception {
		model.addAttribute("user", getCompleteMemberProfile());
		model.addAttribute("industries", ctSvc.getByCode(CommonText.INDUSTRY));
		return "member/editprofile.jsp";
	}

	/**
	 * Upload Profile Picture
	 */
	@RequestMapping(value = "/xuppg", method = RequestMethod.POST)
	public ResponseEntity<String> handleProfilePictureUpload(@RequestParam("profilepic") MultipartFile profilepic) {
		if (!profilepic.isEmpty()) {
			try {
				byte[] bytes = profilepic.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(buildPath(Constants.PROFILE_PIC, "jpg"))));

				stream.write(bytes);
				stream.close();
				return new ResponseEntity<String>("Profile image uploaded Successfully", HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<String>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("No file specified", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Upload Profile Video
	 */
	@RequestMapping(value = "/xupvh", method = RequestMethod.POST)
	public ResponseEntity<String> handleProfileVideoUpload(@RequestParam("profilevideo") MultipartFile profilepic) {
		if (!profilepic.isEmpty()) {
			try {
				byte[] bytes = profilepic.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(buildPath(Constants.PROFILE_VIDEO, "mp4"))));

				stream.write(bytes);
				stream.close();
				return new ResponseEntity<String>("Video uploaded Successfully", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Failed to upload video", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("No file specified", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Serve Video
	 * 
	 */
	@RequestMapping(value = { "/pitch" }, method = RequestMethod.GET)
	public void streamMyPitchGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Path p = Paths.get(buildPath(Constants.PROFILE_VIDEO, "mp4"));
			MultiPartFileSender.fromPath(p).with(request).with(response).serveResource();
		} catch (Exception e) {
			logger.catching(e);
		} finally {

		}
	}

	/**
	 * Serve Profile Picture
	 * 
	 */
	@RequestMapping(value = { "/profilepic" }, method = RequestMethod.GET)
	public void getProfilePic(HttpServletRequest request, HttpServletResponse response) {
		InputStream is = null;
		try {
			File f = new File(buildPath(Constants.PROFILE_PIC, "jpg"));
			if (f.exists()) {
				is = new FileInputStream(f);
				IOUtils.copy(is, response.getOutputStream());
			} else {
				// is =
				// context.getResourceAsStream("../resources/defaultProfilePic.jpg");
			}
		} catch (IOException e) {
			logger.catching(e);
			response.setStatus(500);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	@RequestMapping(value = { "/headline" }, method = RequestMethod.POST)
	public void postProfileHeadline(@ModelAttribute("user") AuthUser user, HttpServletResponse response) {

		try {
			user = memberProfileSvc.updateProfileHeadline(user);
		} catch (Exception e) {
			logger.catching(e);
			response.setStatus(500);
		}
	}

	@RequestMapping(value = { "/summary" }, method = RequestMethod.POST)
	public ResponseEntity<String> postProfileSummary(@RequestParam("summary") String summary,
			HttpServletResponse response) {
		ResponseEntity<String> re = new ResponseEntity<String>("Summary updated successfully", HttpStatus.OK);
		try {
			Profile profile = Utils.getUserProfile();
			profile.setSummary(summary);
			profile = memberProfileSvc.updateSummary(profile);
		} catch (Exception e) {
			logger.catching(e);
			response.setStatus(500);
			re = new ResponseEntity<String>("Error(s) occured while trying to update 'Summary'",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return re;
	}

	@RequestMapping(value = { "/keywords" }, method = RequestMethod.POST)
	public ResponseEntity<String> postProfileKeywords(@RequestParam("keywords") String keywords,
			HttpServletResponse response) {
		ResponseEntity<String> re = new ResponseEntity<String>("Keywords updated successfully", HttpStatus.OK);
		try {
			Profile profile = Utils.getUserProfile();
			profile.setKeywords(keywords);
			profile = memberProfileSvc.updateKeywords(profile);
		} catch (Exception e) {
			logger.catching(e);
			response.setStatus(500);
			re = new ResponseEntity<String>("Error(s) occured while trying to update 'Keywords'",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return re;
	}

	private String getStockPicturePath() {
		/// resources/assets/img/team/img32-md.jpg
		StringBuilder sb = new StringBuilder(env.getProperty("media.base.path"));
		sb.append("/").append(Constants.PROFILE_PIC).append("/").append("stockpicture.jpg");
		return sb.toString();
	}

	private String buildPath(String pathPart, String fileNameExtension) {
		StringBuilder sb = new StringBuilder(env.getProperty("media.base.path"));
		sb.append(File.separator).append(pathPart).append(File.separator).append(Utils.getUserProfile().getUuid())
				.append(".").append(fileNameExtension);
		return sb.toString();
	}

	@RequestMapping(value = { "/members/{id}" }, method = RequestMethod.GET)
	public ResponseEntity<ProfileDTO> getMemberById(@PathVariable("id") Long id, Model model) throws Exception {
		Profile profile = memberProfileSvc.loadProfileByUserId2(new AuthUser(id));
		ProfileDTO profileDto = new ProfileDTO(profile);
		return new ResponseEntity<ProfileDTO>(profileDto, HttpStatus.OK);

	}

	@RequestMapping(value = { "/members/{id}" }, method = RequestMethod.PATCH)
	public ResponseEntity<ProfileDTO> patchMemberProfile(@PathVariable("id") Long id, @RequestBody Profile profile,
			HttpServletResponse response) throws Exception {
		ResponseEntity<ProfileDTO> re = null;
		try {
			profile.setId(id);
			Profile p = memberProfileSvc.patchProfile(profile);
			p = memberProfileSvc.loadProfileById(id);
			ProfileDTO profileDto = new ProfileDTO(p);
			re = new ResponseEntity<ProfileDTO>(profileDto, HttpStatus.OK);
		} catch (AppServiceException e) {
			logger.catching(e);
			response.setStatus(500);
			re = new ResponseEntity<ProfileDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return re;
	}

	

}
