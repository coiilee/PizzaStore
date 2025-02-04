package kh.academy.Pizza.controller;

import kh.academy.Pizza.dto.Pizza;
import kh.academy.Pizza.service.PizzaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api") //공통 주소
public class PizzaApiController {

    @Autowired
   private PizzaServiceImpl pizzaService;

    //메뉴 조회
    @GetMapping("/pizzas")
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizza();
    }

    //메뉴 상세조회
    @GetMapping("/pizzas/{id}")
    public Pizza selectById(@PathVariable int id) {
        return pizzaService.selectById(id);
    }

    //메뉴 추가
    @PostMapping("/pizzas")
    public void createPizza(@RequestParam("name") String name,
                            @RequestParam("price") int price,
                            @RequestParam("description") String description,
                            @RequestParam(value = "image", required = false) MultipartFile imagePath) {
        pizzaService.insertPizza(name,price,description,imagePath);
    }

    //메뉴 수정
    //@RequestBody -> @RequestParam으로 변경 , Multipartfile 추가
    //@RequestPart @RequestParam
    @PutMapping("pizzas/{id}")
    public int updatePizza(@PathVariable int id,  @RequestParam("name") String name,
                           @RequestParam("price") int price,
                           @RequestParam("description") String description,
                           @RequestParam(value = "imagePath", required = false) MultipartFile imagePath) {
                                                          //required = false : null 도 ok 일때 required false 사용함
                                                          //@RequestParam 요청으로 프론트엔드에서 값을 가져올 때 null 값이어도 가능하다는 설정
                                                          //required 를 작성하지 않은 @RequestParam 의 기본값은 required = true 이다.
                                                          //DB에서 not null일 경우 required를 true로 줌. 하지만 null 일 경우 required = false
      /*
      Controller는 주로 Backend <-> Frontend 를 이어주는 중간 다리 역할
      Controller 에서는 front-end에서 가져온 데이터를 @service로 전달하는 역할만 함.
                         DB 에서 Backend 가져온 데이터 @service로 전달
       */
       return pizzaService.updatePizza(id,name,price,description,imagePath);
    }

    //메뉴 삭제
    @DeleteMapping("/pizzas/{id}")
    public void deletePizza(@PathVariable int id) {
        pizzaService.deletePizza(id);
    }
}
