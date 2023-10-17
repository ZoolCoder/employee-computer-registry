package com.zoolcoder.employee.computer.registry.service;


import static io.restassured.RestAssured.given;

import com.zoolcoder.employee.computer.registry.dto.MessageDTO;
import com.zoolcoder.employee.computer.registry.service.impl.AdminNotificationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@SpringBootTest
@Testcontainers
public class AdminNotificationServiceTest {

  @Container
  public static GenericContainer<?> adminNotificationContainer = new GenericContainer<>(
      DockerImageName.parse("greenbone/exercise-admin-notification")).withExposedPorts(8080);
  @InjectMocks
  private AdminNotificationService adminNotificationService;
  @Mock
  private RestTemplate restTemplate;
  @Value("${admin.notification.url}")
  private String adminNotificationUrl;

  @BeforeAll
  public static void setUp() {
    adminNotificationContainer.start();
  }

  @DynamicPropertySource
  static void setAdminNotificationServiceAddress(DynamicPropertyRegistry registry) {
    registry.add("admin.notification.url",
        () -> "http://" + adminNotificationContainer.getHost() + ":"
            + adminNotificationContainer.getFirstMappedPort() + "/api/notify");
  }

  @Test
  public void testNotifyAdminService() {
    // Define the notification payload
    MessageDTO messageDTO = createMessageDTO();

    // Send the POST request to the admin notification service using RestAssured
    given().contentType("application/json").body(messageDTO).when().post(adminNotificationUrl)
        .then().statusCode(HttpStatus.OK.value());
  }

  private MessageDTO createMessageDTO() {
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setLevel("warning");
    messageDTO.setMessage("mmu");
    messageDTO.setEmployeeAbbreviation("Test message");
    return messageDTO;
  }
}
