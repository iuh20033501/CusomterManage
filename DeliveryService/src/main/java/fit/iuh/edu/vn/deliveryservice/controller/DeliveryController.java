package fit.iuh.edu.vn.deliveryservice.controller;

import fit.iuh.edu.vn.deliveryservice.entity.Delivery;
import fit.iuh.edu.vn.deliveryservice.repository.DeliveryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/delivery")
public class DeliveryController {

    private DeliveryRepository deliveryRepository;

    @GetMapping("/getAll")
    public List<Delivery> getAllDeliverys() {
        return deliveryRepository.findAll();

    }
    @GetMapping("/findById/{id}")
    public Delivery getDeliveryById(@RequestBody  String id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public Delivery createDelivery(@RequestBody  Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @PutMapping("/update/{id}")
    public Delivery updateDelivery(@PathVariable String id, @RequestBody Delivery deliveryDetails) {
        Delivery delivery = deliveryRepository.findById(id).orElse(null);
        if (delivery != null) {
            delivery.setLocation(deliveryDetails.getLocation());
            delivery.setCustomer(deliveryDetails.getCustomer());
            return deliveryRepository.save(delivery);
        }
        return null;
    }

    @PutMapping("/delete/{id}")
    public Delivery deleteProduct(@PathVariable String id) {
        Delivery product1 = deliveryRepository.findById(id).orElse(null);
        if(product1 != null) {
            deliveryRepository.deleteById(id);
        }
        return null;
    }
}
