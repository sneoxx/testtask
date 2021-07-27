CREATE TABLE client (
	client_id uuid NOT NULL,
	full_name varchar(50) NOT NULL,
	phone varchar(12) NULL,
	email varchar(50) NULL,
	passport_number varchar(10) NOT NULL,
	CONSTRAINT client_pkey PRIMARY KEY (client_id)
);

INSERT INTO client(client_id, full_name, phone, email, passport_number) VALUES
(uuid(),'Ivan Ivanov','79635847969','rtt@mail.ru','2596369745'),
(uuid(),'Petr Petrov','79667847562','uoghi@mail.ru','3195423698'),
(uuid(),'Elena Leva','79565848629','uih0@mail.ru','7954276923');

CREATE TABLE bank (
   client_id uuid NOT NULL,
   credit_advertise_id uuid NOT NULL
);

CREATE TABLE credit (
   credit_id uuid NOT NULL,
   credit_limit varchar(12) NOT NULL,
   interest_rate decimal(4,2) NOT NULL,
   CONSTRAINT credit_pkey PRIMARY KEY (credit_id)
);

INSERT INTO credit(credit_id, credit_limit, interest_rate) VALUES
(uuid(),'1000000','20'),
(uuid(),'3000000','18'),
(uuid(),'5000000','12.2');

CREATE TABLE credit_advertise (
credit_advertise_id uuid NOT NULL,
credit_id uuid NOT NULL,
credit_amount varchar(12)NOT NULL,
CONSTRAINT credit_advertise_pkey PRIMARY KEY (credit_advertise_id)
);

CREATE TABLE credit_graph (
   credit_graph_id uuid NOT NULL,
   payment_date date NOT NULL,
   amount_payment decimal(20,2) NOT NULL,
   body_payment decimal(20,2) NOT NULL,
   interest_payment decimal(20,2) NOT NULL,
   credit_advertise_id uuid NOT NULL,
   CONSTRAINT credit_graph_pkey PRIMARY KEY (credit_graph_id)
);