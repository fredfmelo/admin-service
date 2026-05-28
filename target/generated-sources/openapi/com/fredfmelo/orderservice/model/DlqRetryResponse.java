package com.fredfmelo.orderservice.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DlqRetryResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-28T08:56:48.468512-03:00[America/Sao_Paulo]", comments = "Generator version: 7.15.0")
public class DlqRetryResponse {

  private @Nullable String queue;

  private @Nullable Integer retriedMessages;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime timestamp;

  public DlqRetryResponse queue(@Nullable String queue) {
    this.queue = queue;
    return this;
  }

  /**
   * Get queue
   * @return queue
   */
  
  @Schema(name = "queue", example = "payment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("queue")
  public @Nullable String getQueue() {
    return queue;
  }

  public void setQueue(@Nullable String queue) {
    this.queue = queue;
  }

  public DlqRetryResponse retriedMessages(@Nullable Integer retriedMessages) {
    this.retriedMessages = retriedMessages;
    return this;
  }

  /**
   * Get retriedMessages
   * @return retriedMessages
   */
  
  @Schema(name = "retriedMessages", example = "5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("retriedMessages")
  public @Nullable Integer getRetriedMessages() {
    return retriedMessages;
  }

  public void setRetriedMessages(@Nullable Integer retriedMessages) {
    this.retriedMessages = retriedMessages;
  }

  public DlqRetryResponse timestamp(@Nullable OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
   */
  @Valid 
  @Schema(name = "timestamp", example = "2026-05-28T01:22Z", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timestamp")
  public @Nullable OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@Nullable OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DlqRetryResponse dlqRetryResponse = (DlqRetryResponse) o;
    return Objects.equals(this.queue, dlqRetryResponse.queue) &&
        Objects.equals(this.retriedMessages, dlqRetryResponse.retriedMessages) &&
        Objects.equals(this.timestamp, dlqRetryResponse.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(queue, retriedMessages, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DlqRetryResponse {\n");
    sb.append("    queue: ").append(toIndentedString(queue)).append("\n");
    sb.append("    retriedMessages: ").append(toIndentedString(retriedMessages)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

