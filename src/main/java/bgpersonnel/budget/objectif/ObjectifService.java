package bgpersonnel.budget.objectif;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ObjectifService {

    @Autowired
    private ObjectifRepository objectifRepository;

    @Autowired
    private JavaMailSender mailSender;
    // CRUD operations

    public Objectif createObjectif(Objectif objectif) {
        return objectifRepository.save(objectif);
    }

    public Objectif findById(Long id) {
        return objectifRepository.findById(id).get();
    }

    public Iterable<Objectif> findAll() {
        return objectifRepository.findAll();
    }

    public Objectif update(Objectif objectif) {
        return objectifRepository.save(objectif);
    }

    public void deleteById(Long id) {
        objectifRepository.deleteById(id);
    }

    public List<Objectif> findByUser(Long id) {
        return objectifRepository.findByUser(id);
    }


    public Double calculateProgressPercentage(Long objectifId) {
        Objectif objectif = objectifRepository.findById(objectifId).orElse(null);
        if (objectif == null) {
            throw new IllegalArgumentException("Objectif invalide");
        }
        Double progress = objectifRepository.calculateProgress(objectif);
        return progress / objectif.getAmount() * 100.0;
    }

    public boolean isObjectifAtteint(Long idObjectif) {
        Objectif objectif = objectifRepository.findById(idObjectif).orElse(null);
        double progressPercentage = calculateProgressPercentage(idObjectif);

        if (progressPercentage >= 100.0) {
            String subject = "Objectif atteint : " + objectif.getName();
            String text = "Félicitations, vous avez atteint votre objectif financier " + objectif.getName() + " !";

            // envoyer un email à l'utilisateur
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(objectif.getUser().getEmail());
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            return true;
        } else {
            return false;
        }
    }

}
