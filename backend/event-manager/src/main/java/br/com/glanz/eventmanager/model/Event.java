package br.com.glanz.eventmanager.model;

import br.com.glanz.eventmanager.model.enums.EventStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 100, message = "Título deve ter no máximo 100 caracteres")
    private String title;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    private String description;

    @NotBlank(message = "Localização é obrigatória")
    @Size(max = 200, message = "Localização deve ter no máximo 200 caracteres")
    private String location;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    private String imageUrl;

    // ALTERAÇÃO AQUI: FetchType.EAGER
    @ManyToOne(fetch = FetchType.EAGER) // Carrega o criador junto com o evento
    @JoinColumn(name = "creator_user_id", nullable = false)
    private User creator;

    // ALTERAÇÃO AQUI: FetchType.EAGER
    @ManyToOne(fetch = FetchType.EAGER) // Carrega o cliente contratante junto com o evento
    @JoinColumn(name = "contracting_client_id")
    private User contractingClient;
}