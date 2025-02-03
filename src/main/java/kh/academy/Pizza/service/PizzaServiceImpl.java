package kh.academy.Pizza.service;

import kh.academy.Pizza.dto.Pizza;
import kh.academy.Pizza.mapper.PizzaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService{

    @Autowired
    PizzaMapper pizzaMapper;

    //pizza 메뉴를 목록 형태로 모두 가져오기
    @Override
    public List<Pizza> getAllPizza() {
        return pizzaMapper.getAllPizza();
    }

    //선택한 id에 해당하는 피자 메뉴 1가지를 상세 조회
    @Override
    public Pizza selectById(int id) {
        return pizzaMapper.selectById(id);
    }

    //pizza 메뉴를 추가할 경우 이미지를 저장할 경로를 설정하여 추가
    @Override
    public int insertPizza(Pizza pizza) {
        return pizzaMapper.insertPizza(pizza);
    }

    //피자 메뉴에 피자를 추가할 경우 이미지 저장할 경로를 설정하여 추가
    //기존에 저장된 이미지를 삭제할 수도 있음
    @Override
    public int updatePizza(Pizza pizza) {
        return pizzaMapper.updatePizza(pizza);
    }

    @Override
    public int deletePizza(int id) {
        return pizzaMapper.deletePizza(id);
    }

}
