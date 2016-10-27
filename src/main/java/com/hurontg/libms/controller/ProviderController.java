package com.hurontg.mars.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hurontg.common.exception.AppServiceException;
import com.hurontg.common.util.Constants;
import com.hurontg.common.util.Utils;
import com.hurontg.mars.domain.Project;
import com.hurontg.mars.dto.ProfileList;
import com.hurontg.mars.dto.ProjectDTO;
import com.hurontg.mars.service.MemberProfileService;
import com.hurontg.mars.service.ProjectService;

@Controller
@RequestMapping("/s/xrd")
public class ProviderController extends BaseController {
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(ProviderController.class.getName());

	@Value("${media.base.path}")
	String basePath;

	@Inject
	private MemberProfileService memberProfileSvc;

	@Inject
	private ProjectService prjSvc;
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String getHomePage(Model model) throws Exception {

		model.addAttribute("user", Utils.getAuthUser());
		return "provider/home.jsp";
	}

	@RequestMapping(value = { "/profiles" }, method = RequestMethod.GET)
	public ResponseEntity<ProfileList> searchProfilesByKeywords(@RequestParam("keywords") String keywords,
			HttpServletResponse response) throws Exception {
		ResponseEntity<ProfileList> re = null;
		try {
			ProfileList profiles = memberProfileSvc.searchProfilesByKeywords(keywords);
			re = new ResponseEntity<ProfileList>(profiles, HttpStatus.OK);
		} catch (AppServiceException e) {
			logger.catching(e);
			response.setStatus(500);
			re = new ResponseEntity<ProfileList>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return re;
	}

	/**
	 * Serve Profile Pitch
	 * 
	 */
	@RequestMapping(value = { "/profiles/{uuid}/pitch" }, method = RequestMethod.GET)
	public void streamProfilePitch(@PathVariable("uuid") String uuid, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			Path p = Paths.get(buildPath(Constants.PROFILE_VIDEO, "mp4", uuid));
			MultiPartFileSender.fromPath(p).with(request).with(response).serveResource();
		} catch (Exception e) {
			logger.catching(e);
		} finally {

		}
	}

	private String buildPath(String pathPart, String fileNameExtension, String uuid) {
		StringBuilder sb = new StringBuilder(basePath);
		sb.append(File.separator).append(pathPart).append(File.separator).append(uuid).append(".")
				.append(fileNameExtension);
		return sb.toString();
	}
	
	@RequestMapping(value = { "/recruiter/{recruiterId}/projects" }, method = RequestMethod.POST)
	public ResponseEntity<Project> saveNewProject(@PathVariable("recruiterId") Long recruiterId, 
			@RequestBody ProjectDTO dto, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ResponseEntity<Project> re;
		try {
		  Project project = prjSvc.createProject(dto);
		  project.setName("HOHUM_MODIFIeD_name");
		  re = new ResponseEntity<Project>(project, HttpStatus.OK);
		} catch (AppServiceException e) {
			logger.catching(e);			
			re = new ResponseEntity<Project>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return re;
	}
	
	@RequestMapping(value = { "/recruiter/{recruiterId}/projects" }, method = RequestMethod.GET)
	public ResponseEntity<List<ProjectDTO>> getProjectsByProvider(@PathVariable("recruiterId") Long recruiterId, 			
			HttpServletResponse response) throws IOException {
		ResponseEntity<List<ProjectDTO>> re;
		try {
		  List<ProjectDTO> projects = prjSvc.loadProjectsByProvider(recruiterId);
		  
		  re = new ResponseEntity<List<ProjectDTO>>(projects, HttpStatus.OK);
		} catch (AppServiceException e) {
			logger.catching(e);			
			re = new ResponseEntity<List<ProjectDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return re;
	}

	
}
