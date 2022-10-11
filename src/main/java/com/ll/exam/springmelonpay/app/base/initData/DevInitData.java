package com.ll.exam.springmelonpay.app.base.initData;

import com.ll.exam.springmelonpay.app.member.service.MemberService;
import com.ll.exam.springmelonpay.app.song.service.SongService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevInitData implements InitDataBefore {
    @Bean
    CommandLineRunner initData(MemberService memberService , SongService songService) {
        return args -> {
            before(memberService , songService);
        };
    }
}