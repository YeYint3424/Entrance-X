package com.EntranceX.mm.EntranceX.dto;

import com.EntranceX.mm.EntranceX.models.TicketOrder_History;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class TicketDto {

    @Lob
    @Column(columnDefinition = "longblob")
    private String encodedSD1, encodedSD2, encodedSD3, encodedSD4, encodedSD5,
            encodedVIP1, encodedVIP2, encodedVIP3, encodedVIP4, encodedVIP5,
            encodedVVIP1, encodedVVIP2, encodedVVIP3, encodedVVIP4, encodedVVIP5;
    private TicketOrder_History ticketOrder;
}
