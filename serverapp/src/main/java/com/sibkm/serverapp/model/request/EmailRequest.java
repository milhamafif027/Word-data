package com.sibkm.serverapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {
    private String to;        // Alamat email penerima
    private String subject;   // Subjek email
    private String name;      // Nama pengguna untuk template
    private String barang;    // Nama barang untuk template
}
