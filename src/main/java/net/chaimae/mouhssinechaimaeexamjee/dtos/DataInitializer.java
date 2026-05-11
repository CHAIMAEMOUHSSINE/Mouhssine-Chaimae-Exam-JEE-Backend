package net.chaimae.mouhssinechaimaeexamjee.dtos;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.insurance.backend.entities.*;
import ma.insurance.backend.entities.ContratHabitation.TypeLogement;
import ma.insurance.backend.entities.ContratSante.NiveauCouverture;
import ma.insurance.backend.entities.Paiement.TypePaiement;
import ma.insurance.backend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final AppRoleRepository roleRepository;
    private final AppUserRepository userRepository;
    private final ClientRepository clientRepository;
    private final ContratAutomobileRepository automobileRepository;
    private final ContratHabitationRepository habitationRepository;
    private final ContratSanteRepository santeRepository;
    private final PaiementRepository paiementRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        log.info("=== Initialisation des données de test ===");

        // ---- ROLES ----
        AppRole roleAdmin = createRole("ROLE_ADMIN");
        AppRole roleEmploye = createRole("ROLE_EMPLOYE");
        AppRole roleClient = createRole("ROLE_CLIENT");

        // ---- USERS ----
        createUser("admin", "admin123", "admin@insurance.ma", roleAdmin);
        createUser("employe1", "emp123", "employe1@insurance.ma", roleEmploye);
        createUser("client1", "client123", "client1@insurance.ma", roleClient);
        createUser("client2", "client123", "client2@insurance.ma", roleClient);

        // ---- CLIENTS ----
        Client c1 = clientRepository.save(Client.builder()
                .nom("Ahmed Benali").email("ahmed.benali@email.com").build());
        Client c2 = clientRepository.save(Client.builder()
                .nom("Fatima Zahra Idrissi").email("fz.idrissi@email.com").build());
        Client c3 = clientRepository.save(Client.builder()
                .nom("Youssef Alami").email("y.alami@email.com").build());
        Client c4 = clientRepository.save(Client.builder()
                .nom("Nadia Cherkaoui").email("n.cherkaoui@email.com").build());

        // ---- CONTRATS AUTOMOBILE ----
        ContratAutomobile auto1 = new ContratAutomobile();
        auto1.setDatesouscription(LocalDate.of(2024, 1, 15));
        auto1.setStatut(Contrat.StatutContrat.VALIDE);
        auto1.setDateValidation(LocalDate.of(2024, 1, 20));
        auto1.setMontantCotisation(1200.0);
        auto1.setDureeContrat(12);
        auto1.setTauxCouverture(80.0);
        auto1.setClient(c1);
        auto1.setNumeroImmatriculation("12345-A-1");
        auto1.setMarqueVehicule("Toyota");
        auto1.setModeleVehicule("Corolla");
        automobileRepository.save(auto1);

        ContratAutomobile auto2 = new ContratAutomobile();
        auto2.setDatesouscription(LocalDate.of(2024, 3, 10));
        auto2.setStatut(Contrat.StatutContrat.EN_COURS);
        auto2.setMontantCotisation(900.0);
        auto2.setDureeContrat(6);
        auto2.setTauxCouverture(60.0);
        auto2.setClient(c2);
        auto2.setNumeroImmatriculation("67890-B-2");
        auto2.setMarqueVehicule("Renault");
        auto2.setModeleVehicule("Clio");
        automobileRepository.save(auto2);

        // ---- CONTRATS HABITATION ----
        ContratHabitation hab1 = new ContratHabitation();
        hab1.setDatesouscription(LocalDate.of(2023, 6, 1));
        hab1.setStatut(Contrat.StatutContrat.VALIDE);
        hab1.setDateValidation(LocalDate.of(2023, 6, 5));
        hab1.setMontantCotisation(800.0);
        hab1.setDureeContrat(24);
        hab1.setTauxCouverture(75.0);
        hab1.setClient(c1);
        hab1.setTypeLogement(TypeLogement.APPARTEMENT);
        hab1.setAdresseLogement("12 Rue Hassan II, Casablanca");
        hab1.setSuperficie(85.0);
        habitationRepository.save(hab1);

        ContratHabitation hab2 = new ContratHabitation();
        hab2.setDatesouscription(LocalDate.of(2024, 2, 20));
        hab2.setStatut(Contrat.StatutContrat.EN_COURS);
        hab2.setMontantCotisation(1500.0);
        hab2.setDureeContrat(36);
        hab2.setTauxCouverture(90.0);
        hab2.setClient(c3);
        hab2.setTypeLogement(TypeLogement.MAISON);
        hab2.setAdresseLogement("45 Avenue Mohammed V, Rabat");
        hab2.setSuperficie(150.0);
        habitationRepository.save(hab2);

        // ---- CONTRATS SANTE ----
        ContratSante san1 = new ContratSante();
        san1.setDatesouscription(LocalDate.of(2024, 1, 1));
        san1.setStatut(Contrat.StatutContrat.VALIDE);
        san1.setDateValidation(LocalDate.of(2024, 1, 3));
        san1.setMontantCotisation(2000.0);
        san1.setDureeContrat(12);
        san1.setTauxCouverture(95.0);
        san1.setClient(c2);
        san1.setNiveauCouverture(NiveauCouverture.PREMIUM);
        san1.setNombrePersonnesCouvertes(4);
        santeRepository.save(san1);

        ContratSante san2 = new ContratSante();
        san2.setDatesouscription(LocalDate.of(2024, 4, 15));
        san2.setStatut(Contrat.StatutContrat.EN_COURS);
        san2.setMontantCotisation(600.0);
        san2.setDureeContrat(12);
        san2.setTauxCouverture(50.0);
        san2.setClient(c4);
        san2.setNiveauCouverture(NiveauCouverture.BASIQUE);
        san2.setNombrePersonnesCouvertes(1);
        santeRepository.save(san2);

        ContratSante san3 = new ContratSante();
        san3.setDatesouscription(LocalDate.of(2023, 11, 1));
        san3.setStatut(Contrat.StatutContrat.RESILIE);
        san3.setMontantCotisation(1200.0);
        san3.setDureeContrat(12);
        san3.setTauxCouverture(70.0);
        san3.setClient(c3);
        san3.setNiveauCouverture(NiveauCouverture.INTERMEDIAIRE);
        san3.setNombrePersonnesCouvertes(2);
        santeRepository.save(san3);

        // ---- PAIEMENTS ----
        savePaiement(auto1, LocalDate.of(2024, 2, 1), 100.0, TypePaiement.MENSUALITE);
        savePaiement(auto1, LocalDate.of(2024, 3, 1), 100.0, TypePaiement.MENSUALITE);
        savePaiement(auto1, LocalDate.of(2024, 4, 1), 100.0, TypePaiement.MENSUALITE);
        savePaiement(hab1, LocalDate.of(2023, 7, 1), 800.0, TypePaiement.PAIEMENT_ANNUEL);
        savePaiement(san1, LocalDate.of(2024, 1, 5), 2000.0, TypePaiement.PAIEMENT_ANNUEL);
        savePaiement(san1, LocalDate.of(2024, 2, 1), 500.0, TypePaiement.PAIEMENT_EXCEPTIONNEL);

        log.info("=== Données initialisées avec succès ===");
        log.info("Comptes créés: admin/admin123, employe1/emp123, client1/client123, client2/client123");
    }

    private AppRole createRole(String name) {
        return roleRepository.findByRoleName(name).orElseGet(() ->
                roleRepository.save(AppRole.builder().roleName(name).build()));
    }

    private void createUser(String username, String password, String email, AppRole role) {
        if (!userRepository.existsByUsername(username)) {
            Set<AppRole> roles = new HashSet<>();
            roles.add(role);
            userRepository.save(AppUser.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .email(email)
                    .active(true)
                    .roles(roles)
                    .build());
            log.info("User created: {} ({})", username, role.getRoleName());
        }
    }

    private void savePaiement(Contrat contrat, LocalDate date, Double montant, TypePaiement type) {
        paiementRepository.save(Paiement.builder()
                .contrat(contrat)
                .datePaiement(date)
                .montant(montant)
                .typePaiement(type)
                .build());
    }
}

