package com.example.blog.Board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor // final이 붙어있는 필드에 대한 생성자를 만들어줌
@Controller
public class BoardController {

    // BoardService 의존
    // final 붙이면 new가 안됨
    // => new가 안되는데 다른곳에서 어떻게 쓰는가?
    // => 생성자가 있으면 됨 (@RequiredArgsConstructor가 만듦)
    private final BoardService boardService;

    @GetMapping("/") // get 요청
    public String list(Model model) { // DS(request객체를 model이라는 객체로 랩핑(포장)해서 전달)

        // 별로 안 좋은 코드
        // => 게시글 목록 띄우기에 쓸데없는 content와 created_at도 들고오기 때문
//        List<Board> boardList = boardService.게시글목록보기();
        // "models" => request의 키값
//        model.addAttribute("models", boardList);

        // 좋은 코드로 바꿔보자
        // BoardResponce 클래스 안의 DTO 클래스에 게시글 번호와 제목만 필드를 넣어놓고
        // BoardService와 BoardController에 활용
        List<BoardResponce.DTO> boardList = boardService.게시글목록보기();
        model.addAttribute("models", boardList);

        // 파일명만 적어놓으면 view resolver가 .mustache파일을 templates에서 찾아냄
        return "list"; // view
    }

    @GetMapping("/board/{id}")
    public String ViewDetails(@PathVariable int id, Model model) {
        BoardResponce.DetailsDTO boardDetails = boardService.게시글상세보기(id);
        model.addAttribute("detailModels", boardDetails);

        return "viewDetails"; // view
    }
}