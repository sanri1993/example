package com.sanri.test.testrabbitmq;

import com.sun.istack.internal.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SendBean implements Serializable {
    @NonNull
    private int id;
    @NonNull
    private String name;
    private Date date;

}
