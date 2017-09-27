import airflow
from datetime import datetime
from airflow import DAG
from airflow.operators.dummy_operator import DummyOperator
from airflow.operators import MeetingSchedulerOperator
from airflow.operators import UserRegisterOperator
from airflow.operators import EMailMessengerOperator

default_args = {
        'owner': 'airflow',
        'depends_on_past': False,
        'start_date': airflow.utils.dates.days_ago(2),
        'email': ['airflow@airflow.com'],
        'email_on_failure': True,
        'email_on_retry': True,
        'retries': 1
      }

dag = DAG('contract.onboard_dag', description='The OnBoard DAG for contract',
          schedule_interval='0 12 * * *',
          default_args=default_args,
          start_date=datetime(2017, 3, 20), catchup=False)

dummy_task = DummyOperator(task_id='dummy_task', dag=dag)

user_register_task = UserRegisterOperator(args_operator_param='This is part of a OnBoard Flux',
                                task_id='user_register_task', dag=dag)
meeting_scheduler_task = MeetingSchedulerOperator(args_operator_param='This part of is a OnBoard Flux',
                                task_id='meeting_scheduler_task', dag=dag)
email_messenger_task = EMailMessengerOperator(args_operator_param='This part of is a OnBoard Flux',
                                task_id='email_messenger_task', dag=dag)

dummy_task >> user_register_task >> meeting_scheduler_task >> email_messenger_task
