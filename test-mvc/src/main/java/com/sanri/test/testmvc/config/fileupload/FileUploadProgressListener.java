package com.sanri.test.testmvc.config.fileupload;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class FileUploadProgressListener implements ProgressListener {
    private HttpSession httpSession;
    private static final String PROGRESS = "progress";

    public void setSession(HttpSession session) {
        this.httpSession = session;
    }

    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        FileUploadProgress progress = new FileUploadProgress(pBytesRead, pContentLength, pItems);
        httpSession.setAttribute(PROGRESS,progress);
    }
}
