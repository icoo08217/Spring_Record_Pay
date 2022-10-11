package com.ll.exam.app.song.controller;

import com.ll.exam.app.member.entity.Member;
import com.ll.exam.app.security.dto.MemberContext;
import com.ll.exam.app.song.entity.Song;
import com.ll.exam.app.song.exception.ActorCanNotModifyException;
import com.ll.exam.app.song.exception.ActorCanNotSeeException;
import com.ll.exam.app.song.form.SongForm;
import com.ll.exam.app.song.service.SongService;
import com.ll.exam.util.Ut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/song")
@Slf4j
public class SongController {
    private final SongService songService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String write(@AuthenticationPrincipal MemberContext memberContext , @Valid SongForm songForm) {
        Member author = memberContext.getMember();
        Song song = songService.create(author, songForm.getSubject(), songForm.getContent());

        return "redirect:/song/" + song.getId() + "?msg=" + Ut.url.encode("%d번 음원이 생성되었습니다.".formatted(song.getId()));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/modify")
    public String showModify(@AuthenticationPrincipal MemberContext memberContext, @PathVariable long id, Model model) {
        Song song = songService.findForPrintById(id).get();

        Member actor = memberContext.getMember();

        if (songService.actorCanModify(actor, song) == false) {
            throw new ActorCanNotModifyException();
        }

        model.addAttribute("song", song);

        return "song/modify";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/modify")
    public String modify(@AuthenticationPrincipal MemberContext memberContext, @Valid SongForm songForm, @PathVariable long id) {
        Song song = songService.findById(id).get();
        Member actor = memberContext.getMember();

        if (songService.actorCanModify(actor, song) == false) {
            throw new ActorCanNotModifyException();
        }

        songService.modify(song, songForm.getSubject(), songForm.getContent());
        return "redirect:/song/" + song.getId() + "?msg=" + Ut.url.encode("%d번 음원이 생성되었습니다.".formatted(song.getId()));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public String detail(@AuthenticationPrincipal MemberContext memberContext, @PathVariable Long id, Model model) {
        Song song = songService.findForPrintById(id).get();

        Member actor = memberContext.getMember();

        if (songService.actorCanModify(actor, song) == false) {
            throw new ActorCanNotSeeException();
        }

        model.addAttribute("song", song);

        return "song/detail";
    }
}
