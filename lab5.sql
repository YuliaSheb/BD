TRIGGER
1. 
CREATE TABLE Log(
	LogId INTEGER NOT NULL,
	StateLog TEXT NOT NULL,
	Date TEXT NOT NULL
)

CREATE OR REPLACE FUNCTION auditfunc() RETURNS TRIGGER AS $$
   BEGIN
      IF TG_LEVEL = 'ROW' THEN
    	CASE TG_OP
      		WHEN 'DELETE' THEN INSERT INTO Log(logid, StateLog, date) VALUES (old.ID, 'DELETE',current_timestamp);
      		WHEN 'UPDATE' THEN INSERT INTO Log(logid, StateLog, date) VALUES (new.ID, 'UPDATE',current_timestamp);
      		WHEN 'INSERT' THEN INSERT INTO Log(logid, StateLog, date) VALUES (new.ID, 'INSERT',current_timestamp);
    	END CASE;
  	  END IF;
        RETURN NEW;
   END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER log_trigger AFTER INSERT OR UPDATE OR DELETE ON Account
FOR EACH ROW EXECUTE PROCEDURE auditfunc();

INSERT INTO Account VALUES (11,'testUsers','qwerty')

SELECT * FROM Log
2.
CREATE OR REPLACE FUNCTION funcCost() RETURNS TRIGGER AS $$
   BEGIN
   CASE TG_OP
      WHEN 'INSERT' THEN 
	  IF NEW.Prices > 100 THEN UPDATE Orders SET Prices = NEW.Prices - NEW.Prices * 0.2 WHERE Id = NEW.Id;
	  END IF;
	END CASE;
      RETURN NEW;
   END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER cost_trigger AFTER INSERT ON Orders
FOR EACH ROW EXECUTE PROCEDURE funcCost();

3.
CREATE OR REPLACE FUNCTION funcDelivery() RETURNS TRIGGER AS $$
   BEGIN
      UPDATE Orders SET StateOrder = 'Отправлен на доставку'  WHERE StateOrder = 'Готов';
      RETURN NULL;
   END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delivery_trigger AFTER UPDATE ON Orders
FOR EACH ROW EXECUTE PROCEDURE funcDelivery();
4.
CREATE OR REPLACE FUNCTION funcState() RETURNS TRIGGER AS $$
   BEGIN
      UPDATE Orders SET StateOrder = 'Обрабатывается'  WHERE StateOrder = '';
      RETURN NULL;
   END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER state_trigger AFTER UPDATE ON Orders
FOR EACH ROW EXECUTE PROCEDURE funcState();


PROCEDURE
1.Test
CREATE OR REPLACE PROCEDURE display_message (INOUT msg TEXT)
AS $$
   BEGIN
	RAISE NOTICE 'Procedure Parameter: %', msg ;
   END ;
$$ LANGUAGE plpgsql ;

call display_message('This is my test case');
