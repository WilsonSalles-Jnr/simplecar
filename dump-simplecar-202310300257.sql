PGDMP         9            	    {         	   simplecar    15.3    15.3 4    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            @           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            A           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            B           1262    115900 	   simplecar    DATABASE     �   CREATE DATABASE simplecar WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE simplecar;
                postgres    false                        2615    115901    people    SCHEMA        CREATE SCHEMA people;
    DROP SCHEMA people;
                postgres    false                        2615    115963 	   workorder    SCHEMA        CREATE SCHEMA workorder;
    DROP SCHEMA workorder;
                postgres    false            ^           1247    115971    ItemTypeEnum    TYPE     I   CREATE TYPE workorder."ItemTypeEnum" AS ENUM (
    'PART',
    'TASK'
);
 $   DROP TYPE workorder."ItemTypeEnum";
    	   workorder          postgres    false    6            �            1259    115903    tb_customer    TABLE     �   CREATE TABLE people.tb_customer (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    phone character varying(15) NOT NULL
);
    DROP TABLE people.tb_customer;
       people         heap    postgres    false    5            �            1259    115902    customer_id_seq    SEQUENCE     �   ALTER TABLE people.tb_customer ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME people.customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            people          postgres    false    216    5            �            1259    115918    tb_model    TABLE     b   CREATE TABLE people.tb_model (
    id bigint NOT NULL,
    name character varying(50) NOT NULL
);
    DROP TABLE people.tb_model;
       people         heap    postgres    false    5            �            1259    115917    tb_model_id_seq    SEQUENCE     �   ALTER TABLE people.tb_model ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME people.tb_model_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            people          postgres    false    218    5            �            1259    115933 
   tb_vehicle    TABLE     �   CREATE TABLE people.tb_vehicle (
    id bigint NOT NULL,
    license_plate character varying(7) NOT NULL,
    customer_id bigint NOT NULL,
    model_id bigint NOT NULL
);
    DROP TABLE people.tb_vehicle;
       people         heap    postgres    false    5            �            1259    115932    tb_vehicle_id_seq    SEQUENCE     �   ALTER TABLE people.tb_vehicle ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME people.tb_vehicle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            people          postgres    false    5    220            �            1259    115965    tb_item    TABLE     �   CREATE TABLE workorder.tb_item (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(100),
    type character varying(4) NOT NULL
);
    DROP TABLE workorder.tb_item;
    	   workorder         heap    postgres    false    6            �            1259    116047    tb_item_application    TABLE     �   CREATE TABLE workorder.tb_item_application (
    item_id bigint NOT NULL,
    model_id bigint NOT NULL,
    id bigint NOT NULL
);
 *   DROP TABLE workorder.tb_item_application;
    	   workorder         heap    postgres    false    6            �            1259    116101    tb_item_application_id_seq    SEQUENCE     �   CREATE SEQUENCE workorder.tb_item_application_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE workorder.tb_item_application_id_seq;
    	   workorder          postgres    false    6    223            C           0    0    tb_item_application_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE workorder.tb_item_application_id_seq OWNED BY workorder.tb_item_application.id;
       	   workorder          postgres    false    224            �            1259    115964    tb_tasks_id_seq    SEQUENCE     �   ALTER TABLE workorder.tb_item ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME workorder.tb_tasks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
         	   workorder          postgres    false    6    222            �            1259    116172    tb_workorder    TABLE     Y  CREATE TABLE workorder.tb_workorder (
    id bigint NOT NULL,
    status character varying(10) NOT NULL,
    vehicle_id bigint NOT NULL,
    smashed boolean DEFAULT false,
    scratrched boolean DEFAULT false,
    broken_glass boolean DEFAULT false,
    hole boolean DEFAULT false,
    observation character varying(100),
    samshed boolean
);
 #   DROP TABLE workorder.tb_workorder;
    	   workorder         heap    postgres    false    6            �            1259    116171    tb_workorder_id_seq    SEQUENCE     �   ALTER TABLE workorder.tb_workorder ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME workorder.tb_workorder_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
         	   workorder          postgres    false    226    6            �            1259    116261    tb_workorder_item    TABLE     l   CREATE TABLE workorder.tb_workorder_item (
    workorder_id bigint NOT NULL,
    item_id bigint NOT NULL
);
 (   DROP TABLE workorder.tb_workorder_item;
    	   workorder         heap    postgres    false    6            �           2604    116102    tb_item_application id    DEFAULT     �   ALTER TABLE ONLY workorder.tb_item_application ALTER COLUMN id SET DEFAULT nextval('workorder.tb_item_application_id_seq'::regclass);
 H   ALTER TABLE workorder.tb_item_application ALTER COLUMN id DROP DEFAULT;
    	   workorder          postgres    false    224    223            1          0    115903    tb_customer 
   TABLE DATA           6   COPY people.tb_customer (id, name, phone) FROM stdin;
    people          postgres    false    216   �;       3          0    115918    tb_model 
   TABLE DATA           ,   COPY people.tb_model (id, name) FROM stdin;
    people          postgres    false    218   <       5          0    115933 
   tb_vehicle 
   TABLE DATA           N   COPY people.tb_vehicle (id, license_plate, customer_id, model_id) FROM stdin;
    people          postgres    false    220   .<       7          0    115965    tb_item 
   TABLE DATA           A   COPY workorder.tb_item (id, name, description, type) FROM stdin;
 	   workorder          postgres    false    222   [<       8          0    116047    tb_item_application 
   TABLE DATA           G   COPY workorder.tb_item_application (item_id, model_id, id) FROM stdin;
 	   workorder          postgres    false    223   �<       ;          0    116172    tb_workorder 
   TABLE DATA           �   COPY workorder.tb_workorder (id, status, vehicle_id, smashed, scratrched, broken_glass, hole, observation, samshed) FROM stdin;
 	   workorder          postgres    false    226   �<       <          0    116261    tb_workorder_item 
   TABLE DATA           E   COPY workorder.tb_workorder_item (workorder_id, item_id) FROM stdin;
 	   workorder          postgres    false    227   =       D           0    0    customer_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('people.customer_id_seq', 25, true);
          people          postgres    false    215            E           0    0    tb_model_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('people.tb_model_id_seq', 8, true);
          people          postgres    false    217            F           0    0    tb_vehicle_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('people.tb_vehicle_id_seq', 46, true);
          people          postgres    false    219            G           0    0    tb_item_application_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('workorder.tb_item_application_id_seq', 32, true);
       	   workorder          postgres    false    224            H           0    0    tb_tasks_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('workorder.tb_tasks_id_seq', 48, true);
       	   workorder          postgres    false    221            I           0    0    tb_workorder_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('workorder.tb_workorder_id_seq', 11, true);
       	   workorder          postgres    false    225            �           2606    115907    tb_customer customer_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY people.tb_customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 C   ALTER TABLE ONLY people.tb_customer DROP CONSTRAINT customer_pkey;
       people            postgres    false    216            �           2606    115922    tb_model tb_model_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY people.tb_model
    ADD CONSTRAINT tb_model_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY people.tb_model DROP CONSTRAINT tb_model_pkey;
       people            postgres    false    218            �           2606    115937    tb_vehicle tb_vehicle_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY people.tb_vehicle
    ADD CONSTRAINT tb_vehicle_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY people.tb_vehicle DROP CONSTRAINT tb_vehicle_pkey;
       people            postgres    false    220            �           2606    115969    tb_item tb_item_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY workorder.tb_item
    ADD CONSTRAINT tb_item_pkey PRIMARY KEY (id);
 A   ALTER TABLE ONLY workorder.tb_item DROP CONSTRAINT tb_item_pkey;
    	   workorder            postgres    false    222            �           2606    116265 (   tb_workorder_item tb_workorder_item_pkey 
   CONSTRAINT     |   ALTER TABLE ONLY workorder.tb_workorder_item
    ADD CONSTRAINT tb_workorder_item_pkey PRIMARY KEY (workorder_id, item_id);
 U   ALTER TABLE ONLY workorder.tb_workorder_item DROP CONSTRAINT tb_workorder_item_pkey;
    	   workorder            postgres    false    227    227            �           2606    116180    tb_workorder tb_workorder_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY workorder.tb_workorder
    ADD CONSTRAINT tb_workorder_pkey PRIMARY KEY (id);
 K   ALTER TABLE ONLY workorder.tb_workorder DROP CONSTRAINT tb_workorder_pkey;
    	   workorder            postgres    false    226            �           1259    116319    fki_model_id    INDEX     G   CREATE INDEX fki_model_id ON people.tb_vehicle USING btree (model_id);
     DROP INDEX people.fki_model_id;
       people            postgres    false    220            �           1259    116296    fki_item_id    INDEX     Q   CREATE INDEX fki_item_id ON workorder.tb_item_application USING btree (item_id);
 "   DROP INDEX workorder.fki_item_id;
    	   workorder            postgres    false    223            �           1259    116302    fki_model_id    INDEX     S   CREATE INDEX fki_model_id ON workorder.tb_item_application USING btree (model_id);
 #   DROP INDEX workorder.fki_model_id;
    	   workorder            postgres    false    223            �           1259    116308    fki_vehicle_id    INDEX     P   CREATE INDEX fki_vehicle_id ON workorder.tb_workorder USING btree (vehicle_id);
 %   DROP INDEX workorder.fki_vehicle_id;
    	   workorder            postgres    false    226            �           2606    115938    tb_vehicle customer_id    FK CONSTRAINT     �   ALTER TABLE ONLY people.tb_vehicle
    ADD CONSTRAINT customer_id FOREIGN KEY (customer_id) REFERENCES people.tb_customer(id) ON DELETE CASCADE NOT VALID;
 @   ALTER TABLE ONLY people.tb_vehicle DROP CONSTRAINT customer_id;
       people          postgres    false    3212    216    220            �           2606    116314    tb_vehicle model_id    FK CONSTRAINT     �   ALTER TABLE ONLY people.tb_vehicle
    ADD CONSTRAINT model_id FOREIGN KEY (model_id) REFERENCES people.tb_model(id) NOT VALID;
 =   ALTER TABLE ONLY people.tb_vehicle DROP CONSTRAINT model_id;
       people          postgres    false    3214    220    218            �           2606    116266    tb_workorder_item item_id    FK CONSTRAINT     �   ALTER TABLE ONLY workorder.tb_workorder_item
    ADD CONSTRAINT item_id FOREIGN KEY (item_id) REFERENCES workorder.tb_item(id);
 F   ALTER TABLE ONLY workorder.tb_workorder_item DROP CONSTRAINT item_id;
    	   workorder          postgres    false    3219    222    227            �           2606    116291    tb_item_application item_id    FK CONSTRAINT     �   ALTER TABLE ONLY workorder.tb_item_application
    ADD CONSTRAINT item_id FOREIGN KEY (item_id) REFERENCES workorder.tb_item(id) ON DELETE CASCADE;
 H   ALTER TABLE ONLY workorder.tb_item_application DROP CONSTRAINT item_id;
    	   workorder          postgres    false    223    3219    222            �           2606    116297    tb_item_application model_id    FK CONSTRAINT     �   ALTER TABLE ONLY workorder.tb_item_application
    ADD CONSTRAINT model_id FOREIGN KEY (model_id) REFERENCES people.tb_model(id) ON DELETE CASCADE;
 I   ALTER TABLE ONLY workorder.tb_item_application DROP CONSTRAINT model_id;
    	   workorder          postgres    false    3214    223    218            �           2606    116309    tb_workorder vehicle_id    FK CONSTRAINT     �   ALTER TABLE ONLY workorder.tb_workorder
    ADD CONSTRAINT vehicle_id FOREIGN KEY (vehicle_id) REFERENCES people.tb_vehicle(id) NOT VALID;
 D   ALTER TABLE ONLY workorder.tb_workorder DROP CONSTRAINT vehicle_id;
    	   workorder          postgres    false    226    3217    220            �           2606    116286    tb_workorder_item workorder_id    FK CONSTRAINT     �   ALTER TABLE ONLY workorder.tb_workorder_item
    ADD CONSTRAINT workorder_id FOREIGN KEY (workorder_id) REFERENCES workorder.tb_workorder(id) NOT VALID;
 K   ALTER TABLE ONLY workorder.tb_workorder_item DROP CONSTRAINT workorder_id;
    	   workorder          postgres    false    227    3224    226            1   3   x�32���)��SN��I-V�*���/�4554��4722662����� �j      3      x���tvW.I,*Q043������ 3�c      5      x�31�t�w1��4�42������� ,	�      7   -   x�3���M��/�I��
�E��y�9
�y)���A!\1z\\\ I0�      8      x�3���46����� '�      ;   @   x�34��p��41�L�,�4΀���Լ�b����d ���P��SH����s8Ӹb���� ?�i      <      x�34�4������� 
��     