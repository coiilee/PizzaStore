package kh.academy.Pizza.service;

import kh.academy.Pizza.dto.Pizza;
import kh.academy.Pizza.mapper.PizzaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PizzaServiceImpl implements PizzaService {

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

    @Value("${upload-img}")
    private String uploadImg;

    //피자 메뉴에 피자를 추가할 경우 이미지 저장할 경로를 설정하여 추가
    //기존에 저장된 이미지를 삭제할 수도 있음
    @Override
    public int updatePizza(int id, String name, String description, int price, MultipartFile imagePath) {

        try {
            String imgPath = imagePath.getOriginalFilename(); //이미지에서 가져온 파일이름
            System.out.println("OriginalFilename :" + imgPath);
            //이미지 저장 경로에 이미지 파일을 저장하고,
            File file = new File(uploadImg, imgPath);
            // 어떤 파일을 저장할 것이다 (어디에+어떤 이름으로) = transferTo.
            imagePath.transferTo(file);


            String imageFilePath = "/uploaded/" + imgPath;
            Pizza pizza = new Pizza();
            pizza.setId(id);
            pizza.setName(name);
            pizza.setPrice(price);
            pizza.setDescription(description);
            pizza.setImagePath(imageFilePath);

            // return khtBookMapper.bookUpdate(id, title, author, genre, imgPath);  imgPath = only img File Name Save
            return pizzaMapper.updatePizza(id,name,description,price,imageFilePath); // dbImagePath = /uploaded/FileName save

        } catch (IOException e) {
            //개발자가 컴퓨터 작업에 문제가 있을 때 문제를 확인하는 멘트
            System.out.println("데이터를 수정할 수 없습니다." + e);
            return 0;
        }
    }

        @Override
        public int deletePizza ( int id){
            return pizzaMapper.deletePizza(id); //mapper에 저장된 delete sql 구문 사용
        }

}
