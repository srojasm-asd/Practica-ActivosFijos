#!/bin/bash

echo "******  PostgreSQL initialisation Star ******"

pg_restore -U $POSTGRES_USER -d $POSTGRES_DB -v "/scripts/asd_activos_bak.dump"

echo "******  PostgreSQL initialisation End ******"
