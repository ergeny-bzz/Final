# Project Name
M295 Final
## Description

This project is a Jakarta EE application using Spring Data JPA, Lombok, and Java SDK version 22 for the backend.

## Visuals

![image](https://github.com/ergeny-bzz/Final/assets/114403168/df6a53be-0de3-46af-8f39-6e23cc13976d)


![image](https://github.com/ergeny-bzz/Final/assets/114403168/0b6e255c-194a-4e79-a64a-c757f6023c4d)

## Validation Rules

* @FutureOrPresent: This rule ensures that date values are in the future or present.
* @Size(min = 3, max = 64): This rule ensures that the length of string or varchar fields is between 3 and 64 characters.

## Authorization matrix

### Admin
* Username: admin
* Password: 1234
  
#### Authorizations:
* Access to all Methods

### User

* Username: user
* Password: 123
#### Authorizations:
* Access to POST and GET

## OpenAPI

```yaml

openapi: "3.0.0"
info:
  version: "1"
  title: ""
  description: ""
paths:
  /resources/api/reviews:
    get:
      operationId: "getAllReviews"
      description: ""
      parameters: []
      responses:
        200:
          description: ""
          content:
            application/json:
              schema:
                type: "array"
                items:
                  $ref: "#/components/schemas/reviews"
    post:
      operationId: "create"
      description: ""
      parameters: []
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/reviews"
      responses:
        200:
          description: "Success"
          content:
            application/json:
              schema:
                type: "string"
        401:
          description: "Unauthorized"
          content:
            application/json:
              schema:
                type: "string"
        404:
          description: "Not Found"
          content:
            application/json:
              schema:
                type: "string"
        500:
          description: "Server Error"
          content:
            application/json:
              schema:
                type: "string"
    put:
      operationId: "update"
      description: ""
      parameters: []
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/reviews"
      responses:
        200:
          description: "Success"
          content:
            application/json:
              schema:
                type: "string"
        401:
          description: "Unauthorized"
          content:
            application/json:
              schema:
                type: "string"
        404:
          description: "Not Found"
          content:
            application/json:
              schema:
                type: "string"
        500:
          description: "Server Error"
          content:
            application/json:
              schema:
                type: "string"
  /resources/api/reviews/{Id}:
    get:
      operationId: "GetReviewById"
      description: ""
      parameters:
        - in: "path"
          name: "Id"
          required: true
          schema:
            type: "integer"
      responses:
        200:
          description: ""
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/reviews"
        404:
          description: ""
          content:
            text/plain:
              schema:
                type: "string"
  /resources/api/reviews/recommend/{status}:
    get:
      operationId: "getReviewsByRecommend"
      description: ""
      parameters:
        - in: "path"
          name: "status"
          required: true
          schema:
            type: "boolean"
      responses:
        200:
          description: ""
          content:
            application/json:
              schema:
                type: "string"
        204:
          description: "No Content"
          content:
            application/json:
              schema:
                type: "string"
  /resources/api/reviews/review/{review}:
    get:
      operationId: "getReviewsByReview"
      description: ""
      parameters:
        - in: "path"
          name: "review"
          required: true
          schema:
            type: "string"
      responses:
        200:
          description: ""
          content:
            application/json:
              schema:
                type: "string"
        204:
          description: "No Content"
          content:
            application/json:
              schema:
                type: "string"
  /resources/api/reviews/exists/{id}:
    get:
      operationId: "checkExistenceById"
      description: ""
      parameters:
        - in: "path"
          name: "id"
          required: true
          schema:
            type: "integer"
      responses:
        200:
          description: "true/false"
          content:
            text/plain:
              schema:
                type: "boolean"
        500:
          description: "Server Error"
          content:
            text/plain:
              schema:
                type: "string"
  /resources/api/reviews/delete-by-date/{date}:
    delete:
      operationId: "DeleteReviewsByDate"
      description: ""
      parameters:
        - in: "path"
          name: "date"
          required: true
          schema:
            type: "string"
      requestBody:
        content:
          application/json:
            schema:
              type: "string"
      responses:
        200:
          description: "Deleted"
          content:
            application/json:
              schema:
                type: "string"
        401:
          description: "Unauthorized"
          content:
            application/json:
              schema:
                type: "string"
        404:
          description: "Not Found"
          content:
            application/json:
              schema:
                type: "string"
        500:
          description: "Server Error"
          content:
            application/json:
              schema:
                type: "string"
  /resources/api/reviews/{id}:
    delete:
      operationId: "deleteById"
      description: ""
      parameters:
        - in: "path"
          name: "id"
          required: true
          schema:
            type: "integer"
      requestBody:
        content:
          application/json:
            schema:
              type: "string"
      responses:
        200:
          description: "Deleted"
          content:
            application/json:
              schema:
                type: "string"
        401:
          description: "Unauthorized"
          content:
            application/json:
              schema:
                type: "string"
        404:
          description: "Not Found"
          content:
            application/json:
              schema:
                type: "string"
        500:
          description: "Server Error"
          content:
            application/json:
              schema:
                type: "string"
  /resources/api/reviews/bulk-create:
    post:
      operationId: "createMultiple"
      description: ""
      parameters: []
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/reviews"
      responses:
        200:
          description: "Success"
          content:
            application/json:
              schema:
                type: "string"
        401:
          description: "Unauthorized"
          content:
            application/json:
              schema:
                type: "string"
        500:
          description: "Server Error"
          content:
            application/json:
              schema:
                type: "string"
components:
  securitySchemes: {}
  schemas:
    trips:
      properties:
        tripId:
          nullable: false
          type: "integer"
        destination:
          nullable: false
          type: "string"
    reviews:
      properties:
        ReviewID:
          nullable: false
          type: "integer"
        Review:
          nullable: false
          type: "string"
        createdAt:
          nullable: false
          type: "string"
        Recommend:
          nullable: false
          type: "boolean"
        wordCount:
          nullable: false
          type: "integer"
        tripId:
          nullable: false
          $ref: "#/components/schemas/trips"
```
