# coindesk
interview


CREATE MEMORY TABLE "PUBLIC"."COIN"(

    "ID" INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,

    "BPI" JSON,

    "CHART_NAME" CHARACTER VARYING(255),

    "DISCLAIMER" CHARACTER VARYING(255),

    "TIME" TIMESTAMP

);
