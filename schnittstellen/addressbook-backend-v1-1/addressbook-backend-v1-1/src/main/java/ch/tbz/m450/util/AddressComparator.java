package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        return Comparator.comparing(Address::getLastname)
                .thenComparing(Address::getFirstname)
                .thenComparingInt(Address::getId)
                .compare(a1, a2);
    }
}