1. 
CREATE OR REPLACE FUNCTION auditfunc() RETURNS TRIGGER AS $$
   BEGIN
      INSERT INTO Log(log_id, date) VALUES (new.ID, current_timestamp);
      RETURN NEW;
   END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER log_trigger AFTER INSERT ON Account
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

