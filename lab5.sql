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
	  IF NEW.Prices > 100 THEN INSERT INTO Orders(Id,basketId,StateOrder,prices) VALUES (NEW.Id,NEW.basketId,NEW.StateOrder,New.Prices = NEW.Prices - NEW.Prices * 0.2 );
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
      UPDATE Orders SET NEW.StateOrder = 'Отправлен на доставку'  WHERE StateOrder = 'Готов';
      RETURN NEW;
   END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delivery_trigger AFTER UPDATE ON Orders
FOR EACH ROW EXECUTE PROCEDURE funcDelivery();

