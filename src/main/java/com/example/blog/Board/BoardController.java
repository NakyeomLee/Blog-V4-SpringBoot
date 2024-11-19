package com.example.blog.Board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor // final이 붙어있는 필드에 대한 생성자를 만들어줌
@Controller
public class BoardController {

    // BoardService 의존
    // final 붙이면 new가 안됨
    // => new가 안되는데 다른곳에서 어떻게 쓰는가?
    // => 생성자가 있으면 됨 (@RequiredArgsConstructor가 만듦)
    private final BoardService boardService;
    
    // 1119 글수정
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BorderRequest.UpdateDTO updateDTO) {
        boardService.게시글수정하기(id, updateDTO);

        return "redirect:/board/" + id; // 수정할 게시글 상세보기로 리다이렉트
    }

    // 1118 글삭제
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        boardService.게시글삭제(id);
        return "redirect:/"; // 메인(게시글 리스트)으로 리다이렉트
    }

    // 1118 글수정
    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, Model model) {
        BoardResponce.UpdateFormDTO updateFormDTO = boardService.게시글수정화면보기(id);
        model.addAttribute("model", updateFormDTO);
        return "update-form"; // view
    }

    // 1118 글쓰기
    @GetMapping("/board/save-form")
    public String saveForm() {
        return "save-form"; // view
    }

    // 1118 글쓰기 (String일 경우)
    // x-www는 클래스로 받을 수 있다 -요청DTO생성
    @PostMapping("/board/save")
    public String save(BorderRequest.SaveDTO saveDTO, HttpServletResponse response) throws IOException {
        System.out.println(saveDTO); // @Data는 내부에 String을 재정의해서 구현해준다(자동 호출)
        boardService.게시글쓰기(saveDTO);
        return "redirect:/"; // 게시글 목록보기로 리다이렉트
    }

    // 1118 글쓰기 (void일 경우)
//    @PostMapping("/board/save")
//    public void save(BoardRequest.SaveDTO saveDTO, HttpServletResponse response) throws IOException { // x-www는 클래스로 받을 수 있다.
//        System.out.println(saveDTO); // @Data는 내부에 toString을 재정의해서 구현해준다.
//        boardService.게시글쓰기(saveDTO);
    // 요청과 응답이 두 번 일어남 => 게시글을 저장하고, 게시글 목록보기를 보여줌
//        response.setStatus(302); // header 302
//        response.setHeader("Location", "/");
//        // return "redirect:/";
//    }

    // 1118 글상세보기
    /**
     * 주소 형태로 데이터 받기 :주소는 모두 where절에 걸림(where절은 쿼리문 조건절을 말한다)
     * 쿼리스트링(where절): /board?title=바다 (제목이'바다'인 글을 찾아줘)
     -기본키나 유니크 하지않을 때. ?key=value 형태. &(and)사용가능
     * 패스변수(where절): /board/1 (id값이 1인 글을 찾아줘)
     -유니크(pk)할 때. URL의 경로 일부를 변수로 사용
     *
     * RESTful API 설계 : 주소로 자원을 찾는 방법. 대화처럼 느껴져야 한다
     쿼리스트링과 패스변수를 적절히 혼합하여 사용
     */
    @GetMapping("/board/{id}") // 중괄호있으면,
    public String detail(@PathVariable("id") int id, Model model) { // 어노테이션 vaule값 필요
        // model을 리퀘스트 객체에 넣어야 꺼내 쓰기 편함 > Model model 추가
        BoardResponce.DetailDTO boardDetail = boardService.게시글상세보기(id);
        model.addAttribute("model", boardDetail);
        return "detail"; // view
    }

    @GetMapping("/") // get 요청
    public String list(Model model) { // DS(request객체를 model이라는 객체로 랩핑(포장)해서 전달)

        // 별로 안 좋은 코드인 이유?
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
}