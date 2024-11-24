package com.example.blog.Board;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.util.Resp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    // 메인화면이자 전체 글 조회
    // 객체를 반환하면 JSON으로 바꿔서 보내준다.
    @GetMapping("/api")
    public Resp<?> list() {
        // Resp<?> extends Object -> 와일드 카드
        List<BoardResponce.ReadDTO> boardList = boardService.게시글목록보기();

        return Resp.ok(boardList);
        // return Resp.ok(boardService.게시글목록보기()); 1줄도 가능
    }

    // 글 상세보기 만들기
    @GetMapping("/api/board/{id}")
    public Resp<?> detail(@PathVariable("id") Integer id) {
        BoardResponce.DetailDTO boardDetail = boardService.게시글상세보기(id);
        return Resp.ok(boardDetail);
    }

    // 글 작성 폼으로 이동 -> 클라이언트에서 처리 가능 -> 필요없는 메서드
//    @GetMapping("/board/save-form")
//    public String saveForm() {
//        return "save-form";
//    }

    // 글 작성
    @PostMapping("/api/board") // api인데 insert한다 (post니까). save 없어도 된다.
    public Resp<?> save(@Valid @RequestBody BoardRequest.SaveDTO saveDTO, Errors errors) {
        boardService.게시글쓰기(saveDTO);
        return Resp.ok(null); // 돌려줄 내용은 없다.
    }

    // 업데이트 폼으로 이동 -> 그림은 프론트에서 이동가능
//    @GetMapping("/board/{id}/update-form")
//    public String updateForm(@PathVariable("id") Integer id, Model model) {
//        BoardResponse.UpdateFormDTO updateFormDTO = boardService.게시글수정화면보기(id);
//        model.addAttribute("model", updateFormDTO);
//        return "update-form";
//    }

    // 글 업데이트
    @PutMapping("/api/board/{id}")
    public Resp<?> updateBoard(@PathVariable("id") Integer id, @Valid @RequestBody BoardRequest.UpdateDTO updateDTO, Errors errors) {
        // 주소에 걸린 id는 무조건 where절에 들어가는 변수다. DTO에 id가 있으면 안된다.
        boardService.게시글수정하기(id, updateDTO);
        return Resp.ok(null);
    }

    // 글 삭제
    @DeleteMapping("/api/board/{id}") // 주소에 동사(delete)는 적지 않는다.
    public Resp<?> delete(@PathVariable Integer id) {
        // body 받는 method는 post, put 2개밖에 없다.
        boardService.게시글삭제(id);
        return Resp.ok(null);
    }
}


//@RequiredArgsConstructor // final이 붙어있는 필드에 대한 생성자를 만들어줌
//@Controller
//public class BoardController {
//
//    // BoardService 의존
//    // final 붙이면 new가 안됨
//    // => new가 안되는데 다른곳에서 어떻게 쓰는가?
//    // => 생성자가 있으면 됨 (@RequiredArgsConstructor가 만듦)
//    private final BoardService boardService;
//
//    // 1119 글수정
//    @PostMapping("/board/{id}/update")
//    public String update(@PathVariable("id") int id, @Valid BoardRequest.UpdateDTO updateDTO, Errors errors) {
//
//        if (errors.hasErrors()) { // 에러를 가졌는가?
//
//            String errMsg = errors.getFieldErrors().get(0).getField() + " : "
//                    + errors.getFieldErrors().get(0).getDefaultMessage();
//            throw new Exception400(errMsg);
//        }
//
//        boardService.게시글수정하기(id, updateDTO);
//
//        return "redirect:/board/" + id; // 수정할 게시글 상세보기로 리다이렉트
//    }
//
//    // 1118 글삭제
//    @PostMapping("/board/{id}/delete")
//    public String delete(@PathVariable("id") int id) {
//        boardService.게시글삭제(id);
//        return "redirect:/"; // 메인(게시글 리스트)으로 리다이렉트
//    }
//
//    // 1118 글수정 폼 나타내기
//    @GetMapping("/board/{id}/update-form")
//    public String updateForm(@PathVariable("id") Integer id, Model model) {
//        BoardResponce.UpdateFormDTO updateFormDTO = boardService.게시글수정화면보기(id);
//        model.addAttribute("model", updateFormDTO);
//        return "update-form"; // view
//    }
//
//    // 1118 글쓰기 폼 나타내기
//    @GetMapping("/board/save-form")
//    public String saveForm() {
//        return "save-form"; // view
//    }
//
//    // 1118 글쓰기 (String일 경우)
//    // x-www는 클래스로 받을 수 있다 -요청DTO생성
//    // @Valid : 해당 클래스 찾아내서 유효성 검사
//    // Errors : 에러들을 다 담고 있음
//    @PostMapping("/board/save")
//    public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) {
//
//        if (errors.hasErrors()) { // 에러를 가졌는가?
//
//            String errMsg = errors.getFieldErrors().get(0).getField() + " : "
//                    + errors.getFieldErrors().get(0).getDefaultMessage();
//            throw new Exception400(errMsg);
//        }
//
//        boardService.게시글쓰기(saveDTO);
//        return "redirect:/"; // 게시글 목록보기로 리다이렉트
//    }
//
//    // 1118 글상세보기
//    /**
//     * 주소 형태로 데이터 받기 :주소는 모두 where절에 걸림(where절은 쿼리문 조건절을 말한다)
//     * 쿼리스트링(where절): /board?title=바다 (제목이'바다'인 글을 찾아줘)
//     -기본키나 유니크 하지않을 때. ?key=value 형태. &(and)사용가능
//     * 패스변수(where절): /board/1 (id값이 1인 글을 찾아줘)
//     -유니크(pk)할 때. URL의 경로 일부를 변수로 사용
//     *
//     * RESTful API 설계 : 주소로 자원을 찾는 방법. 대화처럼 느껴져야 한다
//     쿼리스트링과 패스변수를 적절히 혼합하여 사용
//     */
//    @GetMapping("/board/{id}") // 중괄호있으면,
//    public String detail(@PathVariable("id") Integer id, Model model) { // 어노테이션 vaule값 필요
//        // model을 리퀘스트 객체에 넣어야 꺼내 쓰기 편함 > Model model 추가
//        BoardResponce.DetailDTO boardDetail = boardService.게시글상세보기(id);
//        model.addAttribute("model", boardDetail);
//        return "detail"; // view
//    }
//
//    @GetMapping("/") // get 요청
//    public String list(Model model) { // DS(request객체를 model이라는 객체로 랩핑(포장)해서 전달)
//
//        // 별로 안 좋은 코드인 이유?
//        // => 게시글 목록 띄우기에 쓸데없는 content와 created_at도 들고오기 때문
////        List<Board> boardList = boardService.게시글목록보기();
//        // "models" => request의 키값
////        model.addAttribute("models", boardList);
//
//        // 좋은 코드로 바꿔보자
//        // BoardResponce 클래스 안의 DTO 클래스에 게시글 번호와 제목만 필드를 넣어놓고
//        // BoardService와 BoardController에 활용
//        List<BoardResponce.DTO> boardList = boardService.게시글목록보기();
//        model.addAttribute("models", boardList);
//
//        // 파일명만 적어놓으면 view resolver가 .mustache파일을 templates에서 찾아냄
//        return "list"; // view
//    }
//}