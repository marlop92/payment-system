package pl.mlopatka.payment.system.model.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "customers")
@Access(AccessType.FIELD)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gen")
    @SequenceGenerator(name = "id_seq_gen", sequenceName = "id_seq")
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "customer")
    private Set<InternalAccount> accounts;

    public Customer() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<InternalAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<InternalAccount> accounts) {
        this.accounts = accounts;
    }
}
