package com.test.nameprojecet.controller;

import com.test.nameprojecet.dto.Articleform;
import com.test.nameprojecet.entity.Article;
import com.test.nameprojecet.repository.ArticleRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class ArticlesController {
    @Autowired //의존성 주입
    private ArticleRepository articleRepository;

    //단일 데이터 조회
    @GetMapping("/articles/{id}") //id를 변수로 사용
    public String show(@PathVariable Long id , Model model){ //URL ID 가져오기

        log.info("id = "+id);
        //1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //2. 모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);

        //3. 뷰 페이지 반환하기
        return "articles/show";
    }
    //전체 데이터 조회
    @GetMapping("/articles")
    public String index(Model model){
        //1.모든 데이터 가져오기
        List<Article> boardlist = articleRepository.findAll();
        //2.모델에 데이터 등록하기
        model.addAttribute("articleList",boardlist);
        //3.뷰 페이지 반환하기
        return "articles/index";
    }
    //데이터 등록
    @GetMapping("/articles/new")
    public String newArticleFrom(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(Articleform form){
//        System.out.println(form.toString());
        log.info(form.toString());

        //1.DTO를 엔티티로 변환
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());

        //1.DTO가 엔티티로 잘 변환 되었는지 확인 출력

        //2.리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());
        return  "redirect:/articles/"+saved.getId();

    }

    //데이터 수정 . 데이터 불러오기
    @GetMapping("/articles/{id}/edit")
    public String editpage(@PathVariable Long id, Model model){
    Article editEntity = articleRepository.findById(id).orElse(null);
    model.addAttribute("editarticle",editEntity);

        return "articles/edit";
    }
    //데이터 수정 . 데이터 수정하기
    @PostMapping("/articles/update")
    public String update(Articleform updateForm){
        log.info(updateForm.toString());
        Article updateArticle = updateForm.updateEntity();
        log.info(updateArticle.toString());
        Article target = articleRepository.findById(updateArticle.getId()).orElse(null);
        if(target != null){
            articleRepository.save(updateArticle);
        }
        log.info(target.toString());
        return  "redirect:/articles/"+target.getId();
    }
    //데이터 삭제하기
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id , RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");
        Article deleteEntity = articleRepository.findById(id).orElse(null);
        log.info(deleteEntity.toString());
        if(deleteEntity != null){
            articleRepository.delete(deleteEntity);
            rttr.addFlashAttribute("msg","삭제됐습니다!");
        }

        return "redirect:/articles";
    }
}
