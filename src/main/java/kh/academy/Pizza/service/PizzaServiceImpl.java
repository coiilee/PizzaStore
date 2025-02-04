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

    //직접적으로 경로를 작성할 경우 파일 위치 노출에 대한 위험이 있기 때문에 properties 작성 후 불러오기 해주는 것
    @Value("${upload-img") //파일이름.properties에서 작성한 변수 이름을 가져와서 변수 이름 내 작성된 경로 사용
    private String uploadImg;

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
    public int insertPizza(String name, int price, String description, MultipartFile imgPath) {
        //insert -> System
        //System.currentTimeMillis()를 이용해 이미지 저장 코드 작성
        //피자 메뉴에 피자를 추가할 경우 이미지를 저장할 경로를 설정해 추가
        //imagePath 앞에 System.currenTimeMillis() 현재시간 추가한 다음
        //pizza 변수에 저장.

        //저장하는 현재시간 + 원본파일명 + 확장자명
       String uniqueFileName= System.currentTimeMillis()+imgPath.getOriginalFilename();

        try {
            File file = new File(uploadImg+uniqueFileName);
            imgPath.transferTo(file); //사용자 컴퓨터에서 가져온 이미지를 내 회사 컴에 저장하겠다
        } catch (IOException e) {
            System.out.println("피자 메뉴 담당 개발자 확인 요망 : 이미지 데이터 저장 안됨");
            e.printStackTrace();//빨간 글씨로 에러를 볼 것인가
            System.out.println(e.getMessage()); //검정 글씨로 에러를 볼것인가
        }

        Pizza pizza = new Pizza();
        pizza.setName(name);
        pizza.setPrice(price);
        pizza.setDescription(description);
        pizza.setImagePath("/uploaded/"+uniqueFileName);

        return pizzaMapper.insertPizza(pizza);

    }



    @Override
    public int updatePizza(int id, String name, int price, String description, MultipartFile imagePath) {

            //프론트엔드 -> 컨트롤러 로 전달받은 데이터 중에서 이미지 경로만 String 으로 DB에 저장한 다음에
            //pizza 라는 변수이름으로 sql 전달할 것

            //이미지를 저장하기 전에 사용자1,사용자2,사용자3...가 저장하는 이미지 파일명이 일치하는 경우 ,
            //마지막에 이미지를 저장하는 사용자의 파일로 모두 덮어쓰기가 되기 때문에
            //각 회사만의 방법으로 이미지 덮어쓰기가 되지 않도록 분류하는 작업
            //1. UUID 2.System.currentTimeStamp() 메서드 사용

            //Universally Unique Identifier 범용 고유 식별자
            //UUID.randomUUID() = 거의 충돌 없는 고유한 식별자 만듬
            //만들어진 식별자 128비트(16바이트) 컴퓨터로 만들어진 값을 사람이 읽을 수 있는 문자열(String)형태로 변환.
            //UUID의 경우 기존에 있던 파일 이름을 임의값 36자리로 변환해서 사용함
            String uuid = UUID.randomUUID().toString(); //8-4-4-4-12 총 36자리 문자열 형식 하이픈(-) 포함


            //1-1. 확장자명을 고객이 작성한 확장자명으로 가져오고 싶을 경우
            String originalFilename = imagePath.getOriginalFilename(); //사용자가 작성한 파일이름,확장명 가져오기
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));// . 뒤에 작성된 확장명 가져오기



            //이미지를 어디에 파일 저장하자 라는 기능 작성 필요 !
            //File -> transferTo 사용하면 저장 끝

        try {// 일단은 사용자 컴퓨터에서 이미지 데이터를 가져와 우리 회사 컴퓨터에 저장해보자 !
//            File file = new File(imagePath.getOriginalFilename()); //프로젝트 폴더 내에 클라이언트에서 가져온 파일이름  저장
            File file = new File(uploadImg +"/"+uuid+ fileExtension); //이미지 저장할 경로와 uuid로 변경한 파일이름 +사용자가 저장한 파일 형식

            //사용자에게서 가져온 파일을 저장하겠다.(회사 컴퓨터에서 uuid로 만든 랜덤이름으로)
            imagePath.transferTo(file);//transferTo() 사용시에는 try-catch 감싸줘야함
        } catch (IOException e) { // 그런데 문제가 생기면 문제가 생겼다 사용자에게 알리고 조치를 취하자 -> 일시중지,작업취소
            System.out.println("이미지 저장 담당자님 파일 저장에 문제가 생겼습니다 !"+e);
        }


            Pizza pizza = new Pizza(); //매개변수 안에 들어있는 것들을(전달받은 데이터를) new Pizza()안에 하나씩 새로 넣어주고 db에 저장할 것 !!
            pizza.setId(id);
            pizza.setName(name);
            pizza.setPrice(price);
            pizza.setDescription(description);

            //DB에 경로를 추가할 때는 WebConfig에 작성한 fake 경로를 파일명 앞에 작성해주기
            //예를 들어 webconfig에서 C://pizza-image/ 경로를 /uploaded/ 라는 경로로 읽도록 설정
            //DB에 가짜이미지경로 + 파일명 + 확장명
            pizza.setImagePath(uploadImg +uuid+ fileExtension);//imagePath.getOriginalFilename() -> uuid로 작성된 파일명 설정

//            return pizzaMapper.updatePizza(id,name,price,description,imagePath);
              return pizzaMapper.updatePizza(pizza);

    }

        @Override
        public int deletePizza ( int id){
            return pizzaMapper.deletePizza(id); //mapper에 저장된 delete sql 구문 사용
        }

}
