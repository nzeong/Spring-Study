package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor //final 붙은 것 가지고 생성자 만들어줌
public class BasicItemController {

    private final ItemRepository itemRepository;

    // @Autowired 컨트롤러 내에 생성자 하나라 생략 가능
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items); // items를 html에 전달하기
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){ // PathVariable에 {itemId}와 같은 변수명 입력해주기
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add") //상품을 새로 등록하는 경우 view만 호출(해당 url로 get 요청일 때), 기본 렌더링은 get으로 하는 듯
    public String addForm(){
        return "basic/addForm";
    }
//    @PostMapping("/add") //상품을 수정하는 경우(근데 url은 같음)
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add") //상품을 수정하는 경우(근데 url은 같음)
    public String addItemV2(@ModelAttribute Item item, Model model){

        itemRepository.save(item);
//        model.addAttribute("item", item); 자동 추가 되기에 생략 가능!
//@ModelAttribute("html 넘겨지는 이름") Item item
        return "basic/item";
    }

//    @PostMapping("/add") //상품을 수정하는 경우(근데 url은 같음)
    public String addItemV3(@ModelAttribute("item") Item item, Model model){

        itemRepository.save(item);
//        model.addAttribute("item", item); 자동 추가 되기에 생략 가능!
//@ModelAttribute("html 넘겨지는 이름") Item item
        return "basic/item";
    }

//    @PostMapping("/add") //상품을 수정하는 경우(근데 url은 같음)
    public String addItemV4(Item item){
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add") //상품을 수정하는 경우(근데 url은 같음)
    public String addItemV5(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add") //상품을 수정하는 경우(근데 url은 같음)
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping ("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct // 실행 전 test 용 데이터
    public void init(){
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }
}
