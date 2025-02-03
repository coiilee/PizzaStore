package kh.academy.Pizza.mapper;

import kh.academy.Pizza.dto.Pizza;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface PizzaMapper {

    //피자 메뉴 모두 조회
    List<Pizza> getAllPizza();

    //피자 상세 조회
    public Pizza selectById(int id);

    //피자 저장 - 자료형
    //void = 저장한 피자 메뉴 개수 확인 or 제대로 저장되었는지 확인 x
    //int = 저장할 피자 메뉴 다수 or 0 이하의 값으로 출력되면 제대로 저장되지 않았음 확인 o
    public int insertPizza(Pizza pizza);

    //피자 메뉴 수정 - 자료형 void or int
    //void = 저장한 피자 메뉴 개수 확인 or 제대로 저장되었는지 확인 x
    //int = 수정할 피자 메뉴 다수 or 0 이하의 값으로 출력되면 제대로 저장되지 않았음 확인 o
    public int updatePizza(Pizza pizza);

    //피자 메뉴 삭제
    //void = 삭제한 피자 메뉴 개수 확인 or 제대로 저장되었는지 확인 x
    //int = 삭제할 피자 메뉴 다수 or 0 이하의 값으로 출력되면 제대로 저장되지 않았음 확인 o
    public int deletePizza(int id);



}
