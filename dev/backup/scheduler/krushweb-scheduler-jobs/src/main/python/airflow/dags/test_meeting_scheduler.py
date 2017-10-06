from datetime import datetime
from airflow import DAG
from airflow.operators.dummy_operator import DummyOperator
from airflow.operators import MeetingSchedulerOperator

dag = DAG('meeting_scheduler_test_dag', description='A test for the meeting scheduler',
          schedule_interval='0 12 * * *',
          start_date=datetime(2017, 3, 20), catchup=False)

dummy_task = DummyOperator(task_id='dummy_task', dag=dag)

operator_task = MeetingSchedulerOperator(args_operator_param='This is a test.',
                                task_id='meeting_scheduler_operator_task', dag=dag)

dummy_task >> operator_task
