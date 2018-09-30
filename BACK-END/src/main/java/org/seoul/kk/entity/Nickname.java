package org.seoul.kk.entity;

import lombok.*;
import org.seoul.kk.entity.constant.Classification;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_NICKNAME")
public class Nickname {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "classification")
    private Classification classification;

    @Column(name = "word")
    private String word;
}
