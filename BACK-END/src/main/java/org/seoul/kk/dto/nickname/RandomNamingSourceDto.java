package org.seoul.kk.dto.nickname;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RandomNamingSourceDto {
    public Long adjId;
    public String adjProperty;
    public String nounProperty;
}
