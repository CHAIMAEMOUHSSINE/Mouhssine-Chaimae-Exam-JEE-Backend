package net.chaimae.mouhssinechaimaeexamjee.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDTO {
    private long totalClients;
    private long totalContrats;
    private long contratsEnCours;
    private long contratsValides;
    private long contratsResilies;
    private long contratsAutomobile;
    private long contratsHabitation;
    private long contratsSante;
    private double totalCotisations;
    private long totalPaiements;
}