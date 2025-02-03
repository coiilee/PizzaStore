package kh.academy.Pizza.controller;

import kh.academy.Pizza.dto.Pizza;
import kh.academy.Pizza.service.PizzaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") //공통 주소
public class PizzaApiController {

    @Autowired
    PizzaServiceImpl pizzaService;

    //메뉴 조회
    @GetMapping("/pizzas")
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizza();
    }

    //메뉴 상세조회
    @GetMapping("/pizzas/{id}")
    public Pizza pizzaDetail(@PathVariable int id) {
        return pizzaService.selectById(id);
    }


    //메뉴 추가
    @PostMapping("/pizzas")
    public void createPizza(@RequestBody Pizza pizza) {
        pizzaService.insertPizza(pizza);
    }

    //메뉴 수정
    @PutMapping("/pizzas/{id}")
    public void updatePizza(@PathVariable int id, @RequestBody Pizza pizza) {
        pizzaService.updatePizza(pizza);
    }

    //메뉴 삭제
    @DeleteMapping("/pizzas/{id}")
    public void deletePizza(@PathVariable int id) {
        pizzaService.deletePizza(id);
    }
}
