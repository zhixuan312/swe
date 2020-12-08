package com.example.demo.model.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode()
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersUploadRequest implements Serializable {

	@NotBlank
	private String input;
}
