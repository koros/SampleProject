package com.korosmatick.sampleapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korosmatick.sampleapp.HomeController;
import com.korosmatick.sampleapp.dao.ActionDao;
import com.korosmatick.sampleapp.dao.MessageDao;
import com.korosmatick.sampleapp.model.api.ActionableMessage;
import com.korosmatick.sampleapp.model.api.Response;
import com.korosmatick.sampleapp.model.api.ResponseWrapper;
import com.korosmatick.sampleapp.model.db.Action;
import com.korosmatick.sampleapp.model.db.Message;


@Controller
public class ActionableMessagesController {

	private static final Logger logger = LoggerFactory.getLogger(ActionableMessagesController.class);
	
	@Autowired
	private ActionDao actionDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@RequestMapping(value="/api/update")
	public @ResponseBody ResponseWrapper getUpdate(@RequestParam(value = "id", required=false) String actionId) {
		
		ResponseWrapper apiResponse = new ResponseWrapper();
		Response response = new Response();
		try {
			
			response.setStatus("ok");
			ActionableMessage am = new ActionableMessage();
			
			Integer messageId = 1; // default id message id is 1
			
			if (actionId != null) {
				Action action = actionDao.findActionByActionId(Integer.valueOf(actionId));
				messageId = action.getAssociatedResponseMessageId();
			}
			
			Message message = messageDao.findByMessageId(messageId);
			
			am.setMessage(message);
			am.setPosibleActions(actionDao.findAllActionsForMessageId(message.getMessageId()));
			response.setActionableMessage(am);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("error");
		}
		
		apiResponse.setResponse(response);
		
		return apiResponse;
	}
}
