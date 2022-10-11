package com.ll.exam.springmelonpay.app.song.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SongForm {
    @NotEmpty
    private String subject;

    @NotEmpty
    private String content;
}
