package abc.sampleapp.com.sampleapp.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonRootName(value="response")
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class Response {
	
	private double version = 1.0;
	private String status;
	private List<Error> errors;
	
	private ActionableMessage actionableMessage;
	
	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Error> getErrors() {
		return errors;
	}

	public ActionableMessage getActionableMessage() {
		return actionableMessage;
	}

	public void setActionableMessage(ActionableMessage actionableMessage) {
		this.actionableMessage = actionableMessage;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
}

