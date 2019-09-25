package com.zafin.bankingdemo.hello.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;
import static com.lightbend.lagom.javadsl.api.Service.topic;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.api.broker.kafka.KafkaProperties;

/**
 * The Banking service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the Banking Service.
 */
public interface BankingService extends Service {

  /**
   * Example: curl -H "Content-Type: application/json" -X POST -d '{"firstName":
   * "varghese","lastName":"cottagiri","dateOfBirth":"01-01-1998"}' http://localhost:9000/api/banking/customer
   */
  ServiceCall<CreateCustomerMessage, CustomerId> createCustomer();


  /**
   * This gets published to Kafka.
   */
  Topic<BankingEvent> bankingEvents();

  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("banking").withCalls(
        pathCall("/api/banking/customer",  this::createCustomer)
      ).withTopics(
          topic("banking-events", this::bankingEvents)
          // Kafka partitions messages, messages within the same partition will
          // be delivered in order, to ensure that all messages for the same user
          // go to the same partition (and hence are delivered in order with respect
          // to that user), we configure a partition key strategy that extracts the
          // name as the partition key.
          .withProperty(KafkaProperties.partitionKeyStrategy(), BankingEvent::getCustomerId)
        ).withAutoAcl(true);
    // @formatter:on
  }
}
