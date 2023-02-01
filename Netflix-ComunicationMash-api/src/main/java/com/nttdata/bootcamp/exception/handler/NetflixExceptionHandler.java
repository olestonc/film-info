package com.nttdata.bootcamp.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nttdata.bootcamp.exception.NetflixException;
import com.nttdata.bootcamp.exception.NetflixRuntimeException;
import com.nttdata.bootcamp.exception.error.ErrorMessageService;
import com.nttdata.bootcamp.exception.error.ErrorRest;
import com.nttdata.bootcamp.service.responseModel.NetflixResponse;
import com.nttdata.bootcamp.util.constant.ExceptionConstantsUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@SuppressWarnings({ "rawtypes", "unchecked" })
@Log4j2
@RequiredArgsConstructor
public class NetflixExceptionHandler {

    private final ErrorMessageService errorMessageService;

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public NetflixResponse unhandledErrors(final HttpServletRequest req, final Exception ex) {
        logException(ex);
        return new NetflixResponse(ExceptionConstantsUtils.ERROR,
                Long.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());
    }

    @ExceptionHandler({ NetflixException.class })
    @ResponseBody
    public NetflixResponse handleException(final HttpServletRequest request, final HttpServletResponse response,
            final NetflixException ex) {
        logException(ex);
        response.setStatus(ex.getCode());

        final ErrorRest[] errorRestArray = ex.getErrorDtoCollection().stream().map(
                errorDto -> new ErrorRest(errorDto.getCode(), errorMessageService.getCodes().get(errorDto.getCode())))
                .toArray(ErrorRest[]::new);

        return new NetflixResponse(ExceptionConstantsUtils.ERROR, Long.toString(ex.getCode()), ex.getMessage(),
                errorRestArray);
    }

    @ExceptionHandler({ NetflixRuntimeException.class })
    @ResponseBody
    public NetflixResponse handleException(final HttpServletRequest request, final HttpServletResponse response,
            final NetflixRuntimeException ex) {
        return handleException(request, response, ex.getNetflixException());
    }

    private void logException(final Exception exception) {
        log.error(ExceptionUtils.getStackTrace(exception));
    }

}
