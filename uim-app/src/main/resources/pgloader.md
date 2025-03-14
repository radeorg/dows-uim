load database
from mysql://root:shdy123!@192.168.111.103:13306/wes_all
into pgsql://postgres:123456@192.168.111.103:5432/wes_all

CAST type datetime to timestamptz drop default drop not null using zero-dates-to-null,
type date drop not null drop default using zero-dates-to-null

WITH create tables, create indexes, reset sequences,
foreign keys

SET maintenance_work_mem to '128MB', work_mem to '12MB';