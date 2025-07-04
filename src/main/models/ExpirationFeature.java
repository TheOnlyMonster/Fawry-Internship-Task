package main.models;

import main.interfaces.IProductFeature;
import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

public class ExpirationFeature implements IProductFeature<ExpirationFeature> {
    private LocalDate expirationDate;
    
    public ExpirationFeature(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
    
    @Override
    public Class<ExpirationFeature> getFeatureType() {
        return ExpirationFeature.class;
    }
}