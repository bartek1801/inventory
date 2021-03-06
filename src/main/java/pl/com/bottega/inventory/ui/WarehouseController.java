package pl.com.bottega.inventory.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.inventory.api.AddProductHandler;
import pl.com.bottega.inventory.api.PurchaseDto;
import pl.com.bottega.inventory.api.PurchaseHandler;
import pl.com.bottega.inventory.domain.commands.AddProductCommand;
import pl.com.bottega.inventory.domain.commands.PurchaseCommand;

import java.util.Map;

@RestController
public class WarehouseController {

    private AddProductHandler addProductHandler;

    private PurchaseHandler purchaseHandler;

    public WarehouseController(AddProductHandler addProductHandler, PurchaseHandler purchaseHandler) {
        this.addProductHandler = addProductHandler;
        this.purchaseHandler = purchaseHandler;
    }


    @PostMapping("/inventory")
    public void addProduct(@RequestBody AddProductCommand command){
        addProductHandler.handle(command);
    }

    @PostMapping("/purchase")
    public PurchaseDto purchase(@RequestBody Map<String, Integer> map) { //@RequestBody Map<String, Integer> map
        PurchaseCommand command = new PurchaseCommand(map);
        return purchaseHandler.handle(command);
    }

}
