package com.hurontg.mars.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hurontg.common.exception.AppServiceException;
import com.hurontg.mars.domain.CommonText;
import com.hurontg.mars.dto.CommonTextDTO;
import com.hurontg.mars.service.CommonTextService;

@Controller
@RequestMapping("/commontext")
public class CommonTextController extends BaseController {

	@Inject
	private CommonTextService ctSvc;

	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(CommonTextController.class.getName());

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/{code}" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonTextDTO>> getCommonTextByCode(@PathVariable("code") String code,
			HttpServletResponse response) throws Exception {
		ResponseEntity<List<CommonTextDTO>> re = null;
		try {
			List<CommonText> ct = ctSvc.getByCode(code);
			List<CommonTextDTO> ctDto = toDTOList(ct);
			re = new ResponseEntity<List<CommonTextDTO>>(ctDto, HttpStatus.OK);
		} catch (AppServiceException e) {
			response.setStatus(500);
			logger.catching(e);
			re = new ResponseEntity<List<CommonTextDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return re;
	}

	private List<CommonTextDTO> toDTOList(List<CommonText> ctList) {
		List<CommonTextDTO> dtoList = new ArrayList<CommonTextDTO>(ctList.size());
		for(CommonText ct : ctList) {
			CommonTextDTO dto = new CommonTextDTO(ct);
			dtoList.add(dto);
		}
		return dtoList;
	}
}
