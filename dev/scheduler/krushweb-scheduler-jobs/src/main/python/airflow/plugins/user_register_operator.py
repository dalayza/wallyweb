import logging

from airflow.models import BaseOperator
from airflow.plugins_manager import AirflowPlugin
from airflow.utils.decorators import apply_defaults

log = logging.getLogger(__name__)

class UserRegisterOperator(BaseOperator):

    @apply_defaults
    def __init__(self, args_operator_param, *args, **kwargs):
        self.operator_param = args_operator_param
        super(UserRegisterOperator, self).__init__(*args, **kwargs)

    def execute(self, context):
        log.info("User registering... ")
        log.info('operator_param: %s', self.operator_param)

class MeetingSchedulerPlugin(AirflowPlugin):
    name = "user_register_operator_plugin"
    operators = [UserRegisterOperator]
