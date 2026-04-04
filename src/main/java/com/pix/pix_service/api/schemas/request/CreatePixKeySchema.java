package com.pix.pix_service.api.schemas.request;

import com.pix.pix_service.application.dtos.input.CreatePixKeyDTO;
import com.pix.pix_service.application.dtos.input.PixKeyTypeDTO;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class CreatePixKeySchema {

    private static final CPFValidator cpfValidator = new CPFValidator();
    private static final CNPJValidator cnpjValidator = new CNPJValidator();

    static {
        cpfValidator.initialize(null);
        cnpjValidator.initialize(null);
    }

    private String pixKey;

    @NotNull
    private PixKeyTypeSchema pixKeyType;

    public String getPixKey() {
        return pixKey;
    }

    public PixKeyTypeSchema getPixKeyType() {
        return pixKeyType;
    }

    @AssertTrue(message = "Invalid pixKey for the specified pixKeyType")
    private boolean isPixKeyValid() {
        if (pixKeyType == null) {
            return true;
        }

        if (pixKeyType == PixKeyTypeSchema.CPF) return isValidCpf();
        if (pixKeyType == PixKeyTypeSchema.CNPJ) return isValidCnpj();
        if (pixKeyType == PixKeyTypeSchema.EMAIL) return isValidEmail();
        if (pixKeyType == PixKeyTypeSchema.PHONE) return isValidPhone();
        if (pixKeyType == PixKeyTypeSchema.RANDOM) return isValidRandom();

        throw new IllegalArgumentException("Unsupported pixKeyType: " + pixKeyType.name());
    }

    private boolean isValidCpf() {
        return pixKey != null && cpfValidator.isValid(pixKey, null);
    }

    private boolean isValidCnpj() {
        return pixKey != null && cnpjValidator.isValid(pixKey, null);
    }

    private boolean isValidEmail() {
        return pixKey != null
            && pixKey.contains("@")
            && pixKey.length() <= 77;
    }

    private boolean isValidPhone() {
        return pixKey != null && pixKey.matches("^\\+55\\d{10,11}$");
    }

    private boolean isValidRandom() {
        return pixKey == null;
    }

    public CreatePixKeyDTO toDTO() {
        return new CreatePixKeyDTO(
            this.pixKey,
            PixKeyTypeDTO.valueOf(this.pixKeyType.name())
        );
    }
}
