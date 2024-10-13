package com.smart_ski_rent_ver1_2.repositories;

import com.smart_ski_rent_ver1_2.entity.client.Client;
import com.smart_ski_rent_ver1_2.repositories.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ClientRepositoryTest {
   @Autowired
   private ClientRepository clientRepository;
   @Test
   @DisplayName("Zapisywanie pracownika")
    void givenClient_whenSave_thenReturnSavedClient() {
        // given
     Client client1 = new Client(
             1L,
              "Jarek",
               "Tayson",
               "ABC123456",
               123456789);
         // when
    Client save = clientRepository.save(client1);
        // then
      assertThat(save).isNotNull();
      assertThat(save.getIdClient()).isGreaterThan(0);
      assertThat(save).isEqualTo(client1);
    }
    @Test
   public void whenFindByPhoneNumber_thenReturnTrue() {
        // given
      Client client = new Client();
       client.setPhoneNumber(502109888);
       clientRepository.save(client);

        // when
        boolean exists = clientRepository.existsByPhoneNumber(502109888);
        assertThat(exists).isTrue();
   }
}
