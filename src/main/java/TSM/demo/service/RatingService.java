package TSM.demo.service;

import TSM.demo.domain.Rating;
import TSM.demo.domain.User;
import TSM.demo.repository.RatingRepository;
import TSM.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    public void rateUser(String email, String receiverEmail, int star) {
        User sender = userRepository.findByEmail(email);
        User receiver = userRepository.findByEmail(receiverEmail);
        ratingRepository.save(new Rating(sender, receiver, star));
    }

    public double getRatingAverage(int userId) {
        return calculateRatingAverage(ratingRepository.findRatingsByUserId(userId));
    }

    private double calculateRatingAverage(List<Rating> ratings) {
        int sum=0;
        int cnt=0;
        for (Rating rating : ratings) {
            sum += rating.getStar();
            cnt++;
        }
        return (double) sum / cnt;
    }
}
