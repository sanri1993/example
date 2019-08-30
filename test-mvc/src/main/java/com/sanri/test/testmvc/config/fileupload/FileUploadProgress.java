package com.sanri.test.testmvc.config.fileupload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileUploadProgress {
    private long read;
    private long length;
    private int item;
}
