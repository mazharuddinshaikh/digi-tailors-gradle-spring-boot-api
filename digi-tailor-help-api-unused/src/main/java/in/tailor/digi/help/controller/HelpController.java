/**
 * 
 */
package in.tailor.digi.help.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

/**
 * 
 */

@RestController
@RequestMapping("api/userHelp/")
public class HelpController {

	@GetMapping("/test")
	@Operation(summary = "Test If Application is running")
	public ResponseEntity<String> testApi() {
		return ResponseEntity.ok("Help Api working");
	}

//	private UserHelpService userHelpService;
//	private EmailService emailService;
//
//	@Autowired
//	public void setUserHelpService(UserHelpService userHelpService) {
//		this.userHelpService = userHelpService;
//	}
//
//	@Autowired
//	public void setEmailService(EmailService emailService) {
//		this.emailService = emailService;
//	}
//
//	@GetMapping("/v1/appGuide")
//	public ResponseEntity<ApiResponse<List<UserHelpDto>>> getAppGuideList(HttpServletRequest request)
//			throws RecordNotFoundException {
//		String hostUrl = getHostUrl(request);
//		String contextPath = request.getContextPath();
//		String baseUrl = getBaseUrl(contextPath, hostUrl);
//		LOGGER.info("Get user help document");
//		ResponseEntity<ApiResponse<List<UserHelpDto>>> responseEntity = null;
//		List<UserHelpDto> userHelpList = userHelpService.getAppGuideList(baseUrl);
//		if (!DtsUtils.isNullOrEmpty(userHelpList)) {
//			ApiResponse<List<UserHelpDto>> apiResponse = new ApiResponse<>();
//			apiResponse.setHttpStatus(HttpStatus.OK.value());
//			apiResponse.setMessage("Help document found");
//			apiResponse.setResult(userHelpList);
//			responseEntity = ResponseEntity.ok().body(apiResponse);
//		} else {
//			ApiError apiError = new ApiError(HttpStatus.NO_CONTENT.value(), "Help document not found ");
//			throw new RecordNotFoundException(apiError);
//		}
//		return responseEntity;
//	}
//
//	private String getHostUrl(HttpServletRequest request) {
//		return ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString();
//
//	}
//
//	private String getBaseUrl(String contextPath, String hostUrl) {
//		return DtsUtils.isNullOrEmpty(contextPath) ? hostUrl : (hostUrl + contextPath);
//	}
//
//	@PostMapping(value = "v1/contactUs", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
//	public ResponseEntity<ApiResponse<String>> contactUs(
//			@RequestParam(value = "userName", required = false) String userName,
//			@RequestParam(value = "email", required = true) String email,
//			@RequestParam(value = "comments", required = false) String comments,
//			@RequestParam(value = "receivePromotion", required = false) Boolean receivePromotion) throws DtsException {
//		LOGGER.info("Contact us");
//		String message = null;
//		ResponseEntity<ApiResponse<String>> responseEntity = null;
//		ApiError apiError = null;
//		boolean isEmailSent = false;
//		String emailMessage = null;
//
//		if (DtsUtils.isNullOrEmpty(email) || !DtsUtils.isValidEmail(email)) {
//			apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), "Please provide valid email id");
//			throw new DtsException(apiError);
//		}
//
//		if (DtsUtils.isNullOrEmpty(userName)) {
//			userName = email;
//		}
//
//		emailMessage = "Hello " + userName
//				+ ". Thank you for contacting us. Digi Tailors will review you comment. If needed we will connect with you again.";
//
//		isEmailSent = emailService.sendEmail(email, "Digi Tailors - Contact us", emailMessage);
//		if (isEmailSent) {
//			message = "Response added successfully";
//		} else {
//			apiError = new ApiError(HttpStatus.BAD_REQUEST.value(),
//					"Something went wrong while updating response! Please retry");
//			throw new DtsException(apiError);
//		}
//
//		if (!DtsUtils.isNullOrEmpty(message)) {
//			ApiResponse<String> userResponse = new ApiResponse<>();
//			userResponse.setHttpStatus(HttpStatus.OK.value());
//			userResponse.setMessage(message);
//			responseEntity = ResponseEntity.ok().body(userResponse);
//		}
//		return responseEntity;
//	}
}
