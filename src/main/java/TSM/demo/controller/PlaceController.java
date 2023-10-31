package TSM.demo.controller;

import TSM.demo.domain.place.Restaurant;
import TSM.demo.repository.PlaceRepository;
import TSM.demo.repository.query.RestaurantQueryDto;
import TSM.demo.repository.query.RestaurantQueryRepository;
import TSM.demo.service.PlaceService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final RestaurantQueryRepository restaurantQueryRepository;

    @PostMapping("/api/place/restaurant")
    public CreatePlaceResponse createRestaurant(@RequestBody @Valid Restaurant restaurant) {
        int id = placeService.save(restaurant);
        return new CreatePlaceResponse(id);
    }

    @GetMapping("/api/place/restaurants")
    public List<RestaurantQueryDto> showRestaurants() {
        return restaurantQueryRepository.restaurantQueryDtoList();
    }

    @GetMapping("/api/place/restaurant/{id}")
    public RestaurantQueryDto showRestaurant(@PathVariable int id) {
        return restaurantQueryRepository.findRestaurantById(id);
    }


    @Setter
    @Getter
    @ToString
    static class CreatePlaceResponse {
        private int id;

        public CreatePlaceResponse(int id) {
            this.id = id;
        }
    }
}